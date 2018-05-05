"use strict";
var Constant = (function () {
    function Constant(value) {
        this.num = value;
        this.name = 'Constant';
        this.map = {};
        this.map['value'] = '' + this.num;
    }
    Constant.prototype.fValue = function () {
        return this.num;
    };
    Constant.prototype.toString = function () {
        return Constant.numToStr(this.num);
    };
    Constant.prototype.toPostfix = function () {
        return ' ' + Constant.numToStr(this.num);
    };
    Constant.numToStr = function (value) {
        var s = '' + value;
        if (s.lastIndexOf('.0') != -1) {
            s = s.substring(0, s.length - 2);
        }
        return s;
    };
    return Constant;
}());
var Binary = (function () {
    function Binary(l, tok, r) {
        this.left = l;
        this.operator = tok;
        this.right = r;
        this.name = 'Binary';
        this.map = {};
        this.map['left'] = this.left.toString();
        this.map['operator'] = this.operator.toString();
        this.map['right'] = this.right.toString();
    }
    Binary.prototype.fValue = function () {
        if (this.operator == Token.PLUS)
            return this.left.fValue() + this.right.fValue();
        if (this.operator == Token.MINUS)
            return this.left.fValue() - this.right.fValue();
        if (this.operator == Token.STAR)
            return this.left.fValue() * this.right.fValue();
        if (this.operator == Token.SLASH)
            return this.left.fValue() / this.right.fValue();
        return NaN;
    };
    Binary.prototype.toString = function () {
        return this.toString2(this.left, false) + this.operator + this.toString2(this.right, true);
    };
    Binary.prototype.toString2 = function (exp, atRight) {
        var s = exp.toString();
        if (!(exp instanceof Binary))
            return s;
        var prec = this.precedence();
        var p = exp.precedence();
        if (prec < p || (prec == p && !atRight))
            return s;
        return Token.LEFT + s + Token.RIGHT;
    };
    Binary.prototype.toPostfix = function () {
        return this.left.toPostfix() + this.right.toPostfix() + ' ' + this.operator;
    };
    Binary.prototype.precedence = function () {
        if (this.operator == Token.PLUS || this.operator == Token.MINUS)
            return 10;
        if (this.operator == Token.STAR || this.operator == Token.SLASH)
            return 20;
        throw new Error("operation " + this.operator);
    };
    return Binary;
}());
var Parser = (function () {
    function Parser(input) {
        this.lex = new Scanner(input);
        this.tok = Token.EMPTY;
    }
    Parser.prototype.match = function (matchToken) {
        if (this.tok == matchToken)
            this.tok = this.lex.nextToken();
        else
            this.expected(matchToken.toString());
    };
    Parser.prototype.expected = function (s) {
        throw new Error('Expected: ' + s + ', Found: ' + this.tok.toString());
    };
    Parser.prototype.takePoint = function () {
        if (this.model != undefined) {
            var point = this.model.takePoint();
            if (point != undefined)
                return point;
        }
        return new Point(0, 0);
    };
    Parser.prototype.checkPoint = function (point, status, name, region, props) {
        var shape = new RecursiveFunc(point, name, props);
        var state = new State(status, this.tok, region, shape);
        if (this.model != undefined)
            this.model.addState(state);
    };
    Parser.prototype.attachModel = function (model) {
        this.model = model;
    };
    Parser.prototype.parse = function () {
        this.tok = this.lex.nextToken();
        var region = '';
        var point = this.takePoint();
        this.checkPoint(point, StateStatus.DRAW, 'Expression', region);
        var e = this.expr();
        if (e != undefined)
            this.checkPoint(point, StateStatus.DRAW, 'Expression', region, e.map);
        this.match(Token.EOF);
        return e;
    };
    Parser.prototype.expr = function () {
        var region = 'expression';
        var point = this.takePoint();
        this.checkPoint(point, StateStatus.DRAW, 'Expression()', region);
        var e = this.term();
        if (e != undefined)
            this.checkPoint(point, StateStatus.DRAW, e.name, region, e.map);
        var t = this.tok;
        while (t == Token.PLUS || t == Token.MINUS) {
            this.match(t);
            if (e != undefined) {
                var map = {};
                map['left'] = '' + e.toString();
                map['operator'] = t;
                map['right'] = '';
                region = 'PLUSMINUS';
                this.checkPoint(point, StateStatus.DRAW, 'Binary', region, map);
            }
            var term = this.term();
            if (term == undefined || e == undefined)
                return;
            e = new Binary(e, t, term);
            if (e != undefined)
                this.checkPoint(point, StateStatus.DRAW, e.name, region, e.map);
            t = this.tok;
        }
        region = 'EXPRETURN';
        if (e != undefined)
            this.checkPoint(point, StateStatus.ERASE, e.name, region);
        return e;
    };
    Parser.prototype.term = function () {
        var region = 'TERM';
        var point = this.takePoint();
        this.checkPoint(point, StateStatus.DRAW, 'Term()', region);
        var e = this.factor();
        if (e != undefined)
            this.checkPoint(point, StateStatus.DRAW, e.name, region, e.map);
        var t = this.tok;
        while (t == Token.STAR || t == Token.SLASH) {
            this.match(t);
            if (e != undefined) {
                var map = {};
                map['left'] = '' + e.toString();
                map['operator'] = t;
                map['right'] = '';
                region = 'STARSLASH';
                this.checkPoint(point, StateStatus.DRAW, 'Binary', region, map);
            }
            var factor = this.factor();
            if (factor == undefined || e == undefined)
                return;
            e = new Binary(e, t, factor);
            if (e != undefined)
                this.checkPoint(point, StateStatus.DRAW, e.name, region, e.map);
            t = this.tok;
        }
        region = 'TERMRETURN';
        if (e != undefined)
            this.checkPoint(point, StateStatus.ERASE, e.name, region);
        return e;
    };
    Parser.prototype.factor = function () {
        var region = 'FACTOR';
        var point = this.takePoint();
        this.checkPoint(point, StateStatus.DRAW, 'Factor()', region);
        if (this.tok == Token.NUMBER) {
            var c = new Constant(this.lex.nval);
            region = 'CONSTANT';
            this.checkPoint(point, StateStatus.DRAW, c.name, region, c.map);
            region = 'CONSTANTRETURN';
            this.checkPoint(point, StateStatus.ERASE, c.name, region);
            this.match(Token.NUMBER);
            return c;
        }
        if (this.tok == Token.LEFT) {
            this.match(Token.LEFT);
            var e = this.expr();
            if (e != undefined) {
                region = 'LEFT';
                this.checkPoint(point, StateStatus.DRAW, e.name, region, e.map);
                region = 'LEFTRETURN';
                this.checkPoint(point, StateStatus.ERASE, e.name, region);
            }
            this.match(Token.RIGHT);
            return e;
        }
        this.expected("Factor");
    };
    return Parser;
}());
var Scanner = (function () {
    function Scanner(input) {
        this.source = input;
        this.prev = 0;
        this.next = 0;
        this.tok = Token.EMPTY;
        this.nval = 0;
        this.sval = '';
    }
    Scanner.prototype.getNumber = function () {
        while (this.next < this.source.length) {
            var c = this.source.charAt(this.next);
            if (c == '.' || this.isDigit(c))
                this.next++;
            else
                break;
        }
        this.tok = Token.NUMBER;
        var s = this.source.substring(this.prev, this.next);
        this.nval = parseFloat(s);
    };
    Scanner.prototype.isDigit = function (character) {
        return character.length === 1 && /^\d+$/.test(character);
    };
    Scanner.prototype.getIdent = function () {
        while (this.next < this.source.length) {
            var c = this.source.charAt(this.next);
            if (this.isLetter(c) || this.isDigit(c))
                this.next++;
            else
                break;
        }
        this.sval = this.source.substring(this.prev, this.next);
        this.tok = Token.IDENT;
    };
    Scanner.prototype.isLetter = function (character) {
        return character.length === 1 && /[a-z]/i.test(character);
    };
    Scanner.prototype.nextToken = function () {
        this.nval = 0;
        this.sval = '';
        var c;
        do {
            if (this.next >= this.source.length)
                return (this.tok = Token.EOF);
            c = this.source.charAt(this.next++);
        } while (this.isWhiteSpace(c));
        this.prev = this.next - 1;
        if (this.isLetter(c))
            this.getIdent();
        else if (this.isDigit(c))
            this.getNumber();
        else
            this.tok = TokenParser.valueOf(c);
        return this.tok;
    };
    Scanner.prototype.isWhiteSpace = function (character) {
        return character.length === 1 && /\s/.test(character);
    };
    Scanner.prototype.toString = function () {
        var s = this.tok.toString();
        if (this.tok == Token.NUMBER)
            return Constant.numToStr(this.nval);
        if (this.tok == Token.IDENT)
            return this.sval;
        return s;
    };
    return Scanner;
}());
var Token;
(function (Token) {
    Token["EMPTY"] = "";
    Token["LEFT"] = "(";
    Token["RIGHT"] = ")";
    Token["EQUAL"] = "=";
    Token["PERIOD"] = ".";
    Token["SEMCOL"] = ";";
    Token["PLUS"] = "+";
    Token["MINUS"] = "-";
    Token["STAR"] = "*";
    Token["SLASH"] = "/";
    Token["COMMA"] = ",";
    Token["IDENT"] = "ident";
    Token["NUMBER"] = "number";
    Token["EOF"] = "eof";
})(Token || (Token = {}));
var TokenParser = (function () {
    function TokenParser() {
    }
    TokenParser.valueOf = function (character) {
        if (character == '(')
            return Token.LEFT;
        if (character == ')')
            return Token.RIGHT;
        if (character == '=')
            return Token.EQUAL;
        if (character == '.')
            return Token.PERIOD;
        if (character == ';')
            return Token.SEMCOL;
        if (character == '+')
            return Token.PLUS;
        if (character == '-')
            return Token.MINUS;
        if (character == '*')
            return Token.STAR;
        if (character == '/')
            return Token.SLASH;
        if (character == ',')
            return Token.COMMA;
        if (character == 'ident')
            return Token.IDENT;
        if (character == 'number')
            return Token.NUMBER;
        return Token.EOF;
    };
    return TokenParser;
}());
var Stack = (function () {
    function Stack() {
        this.arr = [];
    }
    Stack.prototype.push = function (value) {
        this.arr.push(value);
    };
    Stack.prototype.pop = function () {
        return this.arr.pop();
    };
    Stack.prototype.peek = function () {
        return this.arr[this.arr.length - 1];
    };
    Stack.prototype.isEmpty = function () {
        return this.arr.length == 0;
    };
    return Stack;
}());
var Queue = (function () {
    function Queue() {
        this.arr = [];
    }
    Queue.prototype.push = function (value) {
        this.arr.push(value);
    };
    Queue.prototype.pushPriority = function (value) {
        this.arr.splice(0, 0, value);
    };
    Queue.prototype.pop = function () {
        var element = this.arr[0];
        this.arr.splice(0, 1);
        return element;
    };
    Queue.prototype.peek = function () {
        return this.arr[0];
    };
    Queue.prototype.isEmpty = function () {
        return this.arr.length == 0;
    };
    return Queue;
}());
var Point = (function () {
    function Point(x, y) {
        this.x = x;
        this.y = y;
    }
    return Point;
}());
var RecursiveFunc = (function () {
    function RecursiveFunc(point, name, props) {
        this.name = name;
        this.props = props;
        this.x = point.x;
        this.y = point.y;
    }
    RecursiveFunc.prototype.getPoint = function () {
        return new Point(this.x, this.y);
    };
    return RecursiveFunc;
}());
var Highlighter = (function () {
    function Highlighter(code) {
        this._code = code;
        this.code = code;
        this.regions = {};
        this.stack = new Stack();
        var beginning = -1;
        var finale = -1;
        var region;
        for (var index = 0; index < code.length; index++) {
            var element = code[index];
            if (element == '#') {
                if (beginning == -1) {
                    if (code.substring(index + 1, index + 4) == 'END') {
                        while (code[++index] != '#') { }
                        index++;
                        continue;
                    }
                    beginning = index;
                }
                else {
                    finale = index;
                    region = code.substring(beginning + 1, finale);
                    this.regions[region] = region;
                    beginning = -1;
                    finale = -1;
                }
            }
        }
    }
    Highlighter.prototype.realCode = function () {
        return this.code;
    };
    Highlighter.prototype.adjustCode = function () {
        this.code = this._code;
        for (var key in this.regions) {
            var span = "<span id='" + key.toLowerCase() + "'>";
            var spann = "</span>";
            this.code = this.code.replace('#' + key + '#', span);
            this.code = this.code.replace('#END' + key + '#', spann);
        }
    };
    return Highlighter;
}());
var Region = (function () {
    function Region(tag, beginning, finale) {
        this.tag = tag;
        this.beginning = beginning;
        this.finale = finale;
    }
    return Region;
}());
var RDPController = (function () {
    function RDPController(model, view) {
        this.stateIndexer = -1;
        this.model = model;
        this.view = view;
        this.model.attach(this);
    }
    RDPController.prototype.reinit = function (parserInput) {
        this.view.reinit();
        this.model.parse(parserInput);
        this.stateIndexer = -1;
    };
    RDPController.prototype.takePoint = function () {
        return this.view.takePoint();
    };
    RDPController.prototype.releasePoint = function (point) {
        this.view.releasePoint(point);
    };
    RDPController.prototype.next = function () {
        if (this.stateIndexer < this.model.states.length - 1) {
            this.stateIndexer++;
            var state = this.model.states[this.stateIndexer];
            switch (state.status) {
                case StateStatus.DRAW:
                    if (state.shape != undefined) {
                        this.view.drawShape(state.shape);
                    }
                    break;
                case StateStatus.ERASE:
                    if (state.shape != undefined) {
                        this.view.eraseShape(state.shape);
                    }
                    break;
            }
            this.view.highlight(state.region);
            this.view.updateToken(state.token);
        }
    };
    RDPController.prototype.prev = function () {
    };
    return RDPController;
}());
var RDPModel = (function () {
    function RDPModel() {
        this.states = [];
    }
    RDPModel.prototype.parse = function (input) {
        this.states = [];
        this.parser = new Parser(input);
        this.parser.attachModel(this);
        this.parser.parse();
    };
    RDPModel.prototype.attach = function (controller) {
        this.controller = controller;
    };
    RDPModel.prototype.addState = function (state) {
        this.states.push(state);
        if (state.status == StateStatus.ERASE && state.shape != undefined
            && this.controller != undefined) {
            this.controller.releasePoint(state.shape.getPoint());
        }
    };
    RDPModel.prototype.takePoint = function () {
        if (this.controller != undefined)
            return this.controller.takePoint();
    };
    return RDPModel;
}());
var shapeWidth = 300;
var shapeHeight = 150;
var RDPView = (function () {
    function RDPView() {
        this.WIDTH_CANVAS = 0;
        this.HEIGHT_CANVAS = 0;
        var canvasDiv = document.getElementById('mycanvas');
        this.WIDTH_CANVAS = window.innerWidth;
        this.HEIGHT_CANVAS = window.innerHeight;
        this.canvas = document.createElement('canvas');
        this.canvas.style.width = '100%';
        this.canvas.style.height = '100%';
        if (canvasDiv != null)
            canvasDiv.appendChild(this.canvas);
        this.points = new Queue();
        this.lastGivenPoint = new Point(0, 0);
        this.points.push(this.lastGivenPoint);
        this.highlighter = new Highlighter(_code);
        this.highlighter.adjustCode();
        this.updateCode(this.highlighter.code);
    }
    RDPView.prototype.reinit = function () {
        var canvasDiv = document.getElementById('mycanvas');
        while (canvasDiv.hasChildNodes) {
            var child = canvasDiv.lastChild;
            if (child == null)
                break;
            canvasDiv.removeChild(child);
        }
        this.WIDTH_CANVAS = window.innerWidth;
        this.HEIGHT_CANVAS = window.innerHeight;
        this.canvas = document.createElement('canvas');
        this.canvas.width = this.WIDTH_CANVAS;
        this.canvas.height = this.HEIGHT_CANVAS;
        canvasDiv.appendChild(this.canvas);
        this.points = new Queue();
        this.lastGivenPoint = new Point(0, 0);
        this.points.push(this.lastGivenPoint);
        this.highlighter = new Highlighter(_code);
        this.highlighter.adjustCode();
        this.updateCode(this.highlighter.code);
        this.updateToken(Token.EMPTY);
    };
    RDPView.prototype.highlight = function (area) {
        this.unhighlight();
        if (area == '')
            return;
        var span = document.getElementById(area.toLowerCase());
        if (span != undefined) {
            span.style.color = 'red';
            var fontSize = window.getComputedStyle(span, null).getPropertyValue('font-size');
            span.style.fontSize = parseInt(fontSize) * 1.2 + 'px';
            this.highlighter.stack.push(area);
        }
    };
    RDPView.prototype.unhighlight = function () {
        var region = this.highlighter.stack.peek();
        if (region != undefined) {
            var prevSpan = document.getElementById(region.toLowerCase());
            prevSpan.removeAttribute("style");
        }
    };
    RDPView.prototype.getContext = function () {
        return this.canvas.getContext('2d');
    };
    RDPView.prototype.takePoint = function () {
        var point = this.points.pop();
        if (point == undefined) {
            var x = this.lastGivenPoint.x + shapeWidth;
            var y = this.lastGivenPoint.y;
            if (x > this.canvas.height) {
                x = 0;
                y = y + shapeHeight;
            }
            point = new Point(x, y);
        }
        this.lastGivenPoint = point;
        return point;
    };
    RDPView.prototype.releasePoint = function (point) {
        this.points.pushPriority(point);
    };
    RDPView.prototype.drawShape = function (shape) {
        var counter = 0;
        var largestMeasureKey = 0;
        var largestMeasureValue = 0;
        if (shape.props != undefined) {
            for (var key in shape.props) {
                if (key.length > largestMeasureKey) {
                    largestMeasureKey = key.length;
                }
                var value = shape.props[key];
                if (value.length > largestMeasureValue) {
                    largestMeasureValue = value.length;
                }
                counter++;
            }
            largestMeasureKey *= 7;
            largestMeasureValue *= 7;
            var width = largestMeasureValue + 25;
            var height = 90;
            var rectX = shape.x + largestMeasureKey + 10;
            var rectY = shape.y + 25;
            var rectW = width;
            var rectH = height;
            var ctx = this.getContext();
            this.eraseShape(shape);
            ctx.beginPath();
            ctx.font = "15px Arial";
            ctx.textBaseline = 'top';
            ctx.strokeText(shape.name, rectX, shape.y);
            ctx.strokeRect(rectX, rectY, rectW, rectH);
            var ara = height / counter;
            var row = rectY + ara / 2;
            var seperatorX = rectX;
            var seperatorY = rectY + 1;
            ctx.textBaseline = 'middle';
            for (var key in shape.props) {
                var value = shape.props[key];
                ctx.moveTo(seperatorX, seperatorY);
                ctx.lineTo(seperatorX + rectW, seperatorY);
                ctx.stroke();
                ctx.strokeText(key, shape.x, row);
                ctx.fillText(value, rectX + 10, row);
                seperatorY += ara;
                row += ara;
            }
            ctx.closePath();
        }
        else {
            var ctx = this.getContext();
            this.eraseShape(shape);
            ctx.font = "15px Arial";
            ctx.textBaseline = 'top';
            ctx.strokeText(shape.name, shape.x, shape.y);
            ctx.strokeRect(shape.x, shape.y + 25, 100, 100);
        }
    };
    RDPView.prototype.eraseShape = function (shape) {
        var ctx = this.getContext();
        ctx.clearRect(shape.x - 5, shape.y - 5, shapeWidth + 5, shapeHeight + 5);
    };
    RDPView.prototype.updateToken = function (tok) {
        var docParser = document.getElementById('parser');
        docParser.innerHTML = "TOKEN = " + tok.toString();
    };
    RDPView.prototype.updateCode = function (code) {
        var codeArea = document.getElementById('code');
        if (codeArea != null)
            codeArea.innerHTML = code;
    };
    return RDPView;
}());
var _code = "<h3>Code</h3>\n<pre><code>Expression expr() {\n    #EXPRESSION#\n    Expression e = term();\n    #ENDEXPRESSION#\n    Token t = tok;\n    #PLUSMINUS#\n    while (t == Token.PLUS || t == Token.MINUS)  {\n        match(t);\n        e = new Binary(e, t, term());\n        t = tok;\n    }\n    #ENDPLUSMINUS#\n    #EXPRETURN#\n    return e;\n    #ENDEXPRETURN#\n}\nExpression term() {\n    #TERM#\n    Expression e = factor();\n    #ENDTERM#\n    Token t = tok;\n    #STARSLASH#\n    while (t == Token.STAR || t == Token.SLASH)  {\n        match(t);\n        e = new Binary(e, t, factor());\n        t = tok;\n    }\n    #ENDSTARSLASH#\n    #TERMRETURN#\n    return e;\n    #ENDTERMRETURN#\n}\nExpression factor() {\n    #FACTOR#\n\n    if (tok == Token.NUMBER)  {\n        #CONSTANT#\n        Expression c = new Constant(lex.nval);\n        match(Token.NUMBER);\n        #ENDCONSTANT#\n        #CONSTANTRETURN#\n        return c;\n        #ENDCONSTANTRETURN#\n    }\n    if (tok == Token.LEFT)  {\n        #LEFT#\n        match(Token.LEFT);\n        Expression e = expr();\n        match(Token.RIGHT);\n        #ENDLEFT#\n        #LEFTRETURN#\n        return e;\n        #ENDLEFTRETURN#\n    }\n\n    #ENDFACTOR#\n    expected(\"Factor\");\n    return null;\n}\n</code></pre>";
var State = (function () {
    function State(status, tok, region, shape) {
        this.status = status;
        this.token = tok;
        this.shape = shape;
        this.region = region;
    }
    State.prototype.toString = function () {
        var str = '';
        if (this.shape != undefined)
            str = this.shape.name + '-';
        return str + ' Status: ' + this.status + " Token: " + this.token;
    };
    return State;
}());
var StateStatus;
(function (StateStatus) {
    StateStatus[StateStatus["DRAW"] = 0] = "DRAW";
    StateStatus[StateStatus["ERASE"] = 1] = "ERASE";
    StateStatus[StateStatus["TOKEN"] = 2] = "TOKEN";
})(StateStatus || (StateStatus = {}));
var view = new RDPView();
var model = new RDPModel();
var controller = new RDPController(model, view);
document.addEventListener('keyup', function (e) {
    if (e.key == 'ArrowRight') {
        controller.next();
    }
    else if (e.key == 'ArrowLeft') {
    }
});
var button = document.getElementById('start');
if (button != null)
    button.onclick = function () {
        try {
            var input = document.getElementById("inputExpression").value;
            controller.reinit(input);
            var visual = document.getElementById('visual').style.visibility = 'visible';
            window.location.href = '#code';
        }
        catch (e) {
            var visual = document.getElementById('visual').style.visibility = 'hidden';
        }
    };
//# sourceMappingURL=AllInOne.js.map

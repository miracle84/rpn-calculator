<h1>Reverse Polish Notation Calculator</h1>
Implementation of RPN calculator.
<h2>High level design</h2>
Implementation may separated in 2 parts - client and server, currently it's in one project, but can
separate and communicate remotely(feign client, native and so on).
<h3>Client</h3>
Client implementation consists of <i>ConsoleClient</i>, and fake <i>WebsocketClient</i> - just for showing extension.
<h3>Server</h3>
Server consists of several separated services, it can be split smaller, but it's not goal of implementation.
<h4>Calculator Service</h4>
Consists of working flow and <i>Processors</i> - little logical modules, 
that try to <i>parse</i> (it part can be moved to separated module - possible extension) and process user input, 
according to processor's logic(current processors - <i>NumberProcessor</i> and <i>OperationProcessor</i>).
Service communicate with Operation Service and Stack Service;
<h4>Operation Service</h4>
Provide list of available operations, now implements only 2 double params standard operations (+,-,*,/),<br/>
but it can be extended without any refactoring, just add 2 double params operation,<br/> 
which implements <i>TwoParamOperation</i> interface, or if it will some more complex operation,<br/> 
you must implement <i>Operation</i> interface and make special <i>Processor</i> for work with this type of operation.<br/>
<b>You don't need rewrite some old well working code!!!</b>
<h4>Stack Service</h4>
Implementation of stack, service can be changed for more powerful service without refactoring code.
<br/>
<h4>Possible improvements</h4>
<ol>
<li>Move services to real separated microservices, or add some operations, or clients.</li>
<li>Many possible logical extensions can be done without modification code, only with extension.</li>
<li>Improve test coverage <b>according to possible</b> extension.</li>
<li>...</li>
</ol>
<br/>
<h3>Build and execution</h3>
You can build and run project ad usual spring boot shell application.
<h4>Console commands</h4>
<b>exec</b> - Execute user command - currently number or operation<br/>
<pre>
> exec 10
> 10
> exec 20
> 20
> exec +
> 30
</pre><br/>
<b>clearstack</b> - Clear stack :).<br/> 
<b>oper</b> - Show available operations list.
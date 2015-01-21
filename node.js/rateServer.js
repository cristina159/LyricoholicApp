var http = require('http')
  , io 	 = require('socket.io');

var app = http.createServer();
app.listen(process.env.PORT);

console.log("Starting server with IP "  + process.env.IP + ":" + process.env.PORT);

var io = io.listen(app)
	, nicknames = {};
	
io.sockets.on('connection', function (socket) {

  socket.on('user rating', function (msg) {
    console.log(JSON.stringify(msg, null, 4));
    socket.emit('server message', "Rating of " + msg.rating + " received");
  });
  
});
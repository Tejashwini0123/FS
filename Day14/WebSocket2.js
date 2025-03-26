/*
<!--

Real-Time Group Chat using WebSockets

You are required to build a real-time group chat application using WebSockets, 
where multiple users (clients) can join a chatroom and exchange messages in 
real-time. The application must consist of a WebSocket server and a browser-based 
client. All messages sent by any client must be visible to all other connected 
clients, including the sender — similar to a WhatsApp group.


Functional Requirements:

1. WebSocket Server (Node.js):
------------------------------
You must implement a WebSocket server with the following behavior:
	- Accept connections from multiple clients.
	- Maintain a list of all connected clients.
	- When a message is received from any client:
		- Broadcast that message to all connected clients.
	- Handle client disconnections and remove them from the active list.

Use the ws npm package to create the server. The server should run on ws://localhost:8080.

2. Web-based Client (HTML + JavaScript):
----------------------------------------
You must create a basic client interface with the following requirements:
	- Connect to the WebSocket server at ws://localhost:8080.
	- The page should have:
		- A <div> with id="chat" that shows all chat messages.
		- An <input> box with id="msg" to type the message.
		- A <button> that, when clicked, sends the message.
	- When a message is received from the server:
		- It must be displayed as a new paragraph <p> inside the #chat area.
	- When the user sends a message:
		- It should be sent to the server using WebSocket.
		- The input box should be cleared after sending.
		
================================================================================		
Example URL value=>   http://192.168.xx.xx:5500/index.html

-->

<config>
    <url value="http://192.168.4.192:5500/Client.html"></url>
</config>
--!>

*/
//Server.js
const WebSocket = require('ws');

const server = new WebSocket.Server({ port: 8080 });
const clients = new Set();

server.on('connection', (socket) => {
    console.log('Client connected');
    clients.add(socket);
    socket.on('message', (message) => {
        message = message.toString();
        console.log(`Received: ${message}`);
        for (let client of clients) {
            if (client.readyState === WebSocket.OPEN) {
                client.send(message);
            }
        }
    });
    socket.on('close', () => {
        console.log('Client disconnected');
        clients.delete(socket);
    });
});

console.log('WebSocket server running on ws://localhost:8080');


//Client.html

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Group Chat</title>
</head>
<body>

    <h2>Group Chat</h2>
    <div id="chat"></div>
    <input type="text" id="msg" placeholder="Type a message..." />
    <button onclick="sendMessage()">Send</button>

    <script>
        const socket = new WebSocket('ws://192.168.4.192:8080');
        socket.addEventListener('message', (event) => {
            const chat = document.getElementById('chat');
            const message = document.createElement('p');
            message.textContent = event.data;
            chat.appendChild(message);
        });

        function sendMessage() {
        const input = document.getElementById('msg');
        const message = input.value.trim();
        if (message) {
            socket.send(message); 
            input.value = ''; 
        }   
        }

        socket.addEventListener('close', () => {
            console.log('Disconnected from server');
        });
    </script>
</body>
</html>




/*<!--

Employee Management WebSocket Application

Objective:
-----------
Your task is to develop a WebSocket-based Employee Management System using Node.js. 
This system will allow clients to:
1. Insert Employee Records (INSERT <name> <salary>)
2. Retrieve Employee List (RETRIEVE)
3. Handle Invalid Commands (e.g., INVALID should return "Invalid command")
Your goal is to implement and test a WebSocket-based server and client, ensuring that all operations work correctly.

Requirements:
-------------
1. Implement WebSocket Server
	The server should:
		-> Accept multiple client connections.
		-> Process client messages and handle commands:
			1. INSERT <name> <salary> → Adds an employee to an in-memory array.
			2. RETRIEVE → Returns all stored employees.
			3. Any other command should return "Invalid command."
		-> Maintain an in-memory array of employees (no database required).
		-> Log each received command on the console.
		
		
Expected Behavior
-----------------

============================================================================================
Client Command			Server Response
============================================================================================
INSERT Alice 50000		"Employee inserted successfully."
INSERT Bob 60000		"Employee inserted successfully."
RETRIEVE				"ID: 1, Name: Alice, Salary: 50000
                         ID: 2, Name: Bob, Salary: 60000"
INVALID					"Invalid command."
============================================================================================

Note: 
-> The server should run on port 8080.
-> The system should allow multiple clients to connect.

EXAMPLE URL value=>   ws://10.11.xx.xx:8080

-->

<config>
    <url value="ws://192.168.3.33:8080"></url>
</config>

*/
//Server.js

const ws = require('ws');
const server = new ws.Server({ port: 8080 });
let employees = [];
let nextId = 1;
server.on('connection', (socket) => {
    console.log('Client connected');
    socket.on('message', (message) => {
        console.log(`Received: ${message}`);
        const parts = message.toString().split(' ');
        if (parts[0] === 'INSERT' && parts.length === 3) {
            const name = parts[1];
            const salary = parseFloat(parts[2]);
            if (!isNaN(salary)) {
                employees.push({ id: nextId++, name, salary });
                socket.send('Employee inserted successfully.');
            } else {
                socket.send('Invalid command.');
            }
        } 
        else if (parts[0] === 'RETRIEVE') {
            if (employees.length === 0) {
                socket.send('No employees found.');
            } else {
                const employeeList = employees.map(emp =>
                    `ID: ${emp.id}, Name: ${emp.name}, Salary: ${emp.salary}`
                ).join('\n');
                socket.send(employeeList);
            }
        } 
        else {
            socket.send('Invalid command.');
        }
    });
    socket.on('close', () => {
        console.log('Client disconnected');
    });
});

console.log('WebSocket server is running on ws://localhost:8080');

//Client.js

const ws = require('ws');
const socket = new ws('ws://localhost:8080');

socket.on('open', () => {
    console.log('Connected to server');
    socket.send('INSERT Alice 50000');
    socket.send('INSERT Bob 60000');
    socket.send('RETRIEVE');
    socket.send('INVALID');
});
socket.on('message', (message) => {
    console.log(`Server response: ${message}`);
});
socket.on('close', () => {
    console.log('Disconnected from server');
});

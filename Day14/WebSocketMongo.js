/*<!--
Employee Management WebSocket Application with MongoDB

Objective:
----------
Your task is to develop a WebSocket-based Employee Management System using Node.js and MongoDB. 
The system should allow multiple clients to interact with a database to perform the following operations:
	1. Insert Employee Records (INSERT <name> <salary> <role> <department> <experience>)
	2. Retrieve Employee List (RETRIEVE)
	3. Retrieve Employee List who belongs to a department (RETRIEVE_BY_DEPT <department>)
	
The WebSocket server should be capable of handling multiple concurrent clients and persist employee data in MongoDB.


// MongoDB Employee Schema
const employeeSchema = new mongoose.Schema({
    name: String,
    salary: Number,
    role: String,
    department: String,
    experience: Number
});

Requirements:
-------------
Implement WebSocket Server
	The server should:
		-> Accept multiple client connections. (give a response as "Connected" )
		-> Process incoming commands from clients as discussed above.
		-> Log each received command on the console.
		-> Ensure proper error handling (e.g., invalid salary, missing name, etc.).
		
Expected Behavior
-----------------

============================================================================================
Client Command			                Server Response
============================================================================================
INSERT Alice 50000 Developer IT 5	    "Employee inserted successfully."
INSERT Bob 60000 Manager IT 5	        "Employee inserted successfully."

RETRIEVE				                "ID: 1, Name: Alice, Salary: 50000, Role: Developer, Department: IT, Experience: 5 years"
                                        "ID: 2, Name: Bob, Salary: 60000, Role: Manager, Department: IT, Experience: 5 years"

RETRIEVE_BY_DEPT IT                     "ID: 1, Name: Alice, Salary: 50000, Role: Developer, Department: IT, Experience: 5 years"
                                        "ID: 2, Name: Bob, Salary: 60000, Role: Manager, Department: IT, Experience: 5 years"


INVALID					                "Invalid command."
============================================================================================

Note: 
-> Your implementation must use MongoDB for data persistence.
-> The server should run on port 8080.
-> The system should allow multiple clients to connect.


EXAMPLE URL value=>   ws://10.11.xx.xx:8080

-->
<config>
    <url value="ws://192.168.4.192:8080"></url>
</config>
*/
//Server.js
const mongoose=require('mongoose');
const ws = require('ws');
const server = new ws.Server({ port: 8080 });
let employees = [];
let nextId = 1;
mongoose.connect("mongodb://127.0.0.1:27017/EmployeesDB",{
    useNewUrlParser:true,
    useUnifiedTopology:true,
}).then(()=>{
    console.log("Connected to Mongo")
}).catch(()=>{
    console.log("Error in connecting")
})
const employeeSchema = new mongoose.Schema({
    name: String,
    salary: Number,
    role: String,
    department: String,
    experience: Number
});
const Employee=mongoose.model('employee',employeeSchema)
server.on('connection', (socket) => {
    console.log('Client connected');
    socket.on('message', async (message) => {
        console.log(`Received: ${message}`);
        const parts = message.toString().trim().split(/\s+/);
        if (parts[0] === 'INSERT' && parts.length === 6) {
            const name = parts[1].trim();
            const salary = parseFloat(parts[2].trim());
            const role= parts[3].toString().trim();
            const department=parts[4].trim();
            const experience=parseInt(parts[5].trim());
            if (!name || isNaN(salary) || !role || !department || isNaN(experience)) {
                socket.send('Invalid input. Please check the data types and values.');
                return;
            }
            try {
                const newEmployee = new Employee({ name, salary, role, department, experience});
                await newEmployee.save();
                socket.send('Employee inserted successfully.');
            } catch (err) {
                console.error('Error inserting employee:', err);
                socket.send('Failed to insert employee.');
            }
        } 
        else if (parts[0] === 'RETRIEVE') {
            try {
                const employees = await Employee.find();
                if (employees.length === 0) {
                    socket.send('No employees found.');
                } else {
                    const employeeList = employees.map((emp, index) =>
                        `ID: ${index + 1}, Name: ${emp.name}, Salary: ${emp.salary}, Role: ${emp.role}, Department: ${emp.department}, Experience: ${emp.experience} years`
                    ).join('\n');
                    socket.send(employeeList);
                }
            } catch (err) {
                console.error('Error retrieving employees:', err);
                socket.send('Failed to retrieve employees.');
            }
        } 
        else if (parts[0] === 'RETRIEVE_BY_DEPT' && parts.length === 2) {
            const department = parts[1];

            try {
                const employees = await Employee.find({ department });
                if (employees.length === 0) {
                    socket.send(`No employees found in department: ${department}`);
                } else {
                    const employeeList = employees.map((emp, index) =>
                        `ID: ${index + 1}, Name: ${emp.name}, Salary: ${emp.salary}, Role: ${emp.role}, Department: ${emp.department}, Experience: ${emp.experience} years`
                    ).join('\n');
                    socket.send(employeeList);
                }
            } catch (err) {
                console.error('Error retrieving employees by department:', err);
                socket.send('Failed to retrieve employees by department.');
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
    socket.send('INSERT Alice 50000 Developer IT 5');
    socket.send('INSERT Bob 60000 Manager IT 5');
    socket.send('RETRIEVE');
    socket.send('RETRIEVE_BY_DEPT IT');
    socket.send("Invalid command.")
});
socket.on('message', (message) => {
    console.log(`Server response: ${message}`);
});
socket.on('close', () => {
    console.log('Disconnected from server');
});

/*<!--
Order Management - using JSON Object
Objective: Implement the CRUD operations.

Requirements:
----------------------------------------------------------
Method	Endpoint	            Description
----------------------------------------------------------
POST	   /orders	    		Create a new order
GET	       /orders	    		Get all orders
GET	       /orders/:id	    Get a order by ID
PUT	       /orders/:id		Update a order by ID
DELETE	   /orders/:id		Delete a order by ID
----------------------------------------------------------

Reference JSON format for Object:
--------------------------------------
{ 
	id: 1,
	customerName: "Azar",
	totalPrice: 150.0
}

NOTE: id value starts with 1, and increments by 1, for every new entry.

2. Implementation Requirements:
---------------------------------
Create a JSON Object (local)
Implement proper error handling
Add data validation


3. API Response Format:
------------------------	
Method: POST
Path: /orders

Response:
    If successful:
      res.status(201).send(order);
========================================================

Method: GET
Path: /orders

Response:
    If successful:
      res.send(orders);

========================================================
Method: GET
Path: /orders/:id

NOTE: pass (id value as URI params)

Response:
    If successful:
		res.send(order);
    
    If not found:
        res.status(404).send();
========================================================
Method: PUT
Path: /orders/:id

NOTE: pass (id value as URI params)

Response:
    If successful:
		res.send(order);
    
    If not found:
        res.status(404).send();
==========================================================

Method: DELETE
Path: /orders/:id

NOTE: pass (id value as URI params)

Response:
    If successful:
        res.status(200).send();

-->

<config>
    <url value=""></url>
</config>
*/
const express=require("express")
const cors=require("cors")
const bodyParser = require("body-parser")
const app=express()
PORT=3000
app.use(bodyParser.json())
app.use(cors())
let orders=[
    { 
        id: 1,
        customerName: "Azar",
        totalPrice: 150.0
    },
    { 
        id: 2,
        customerName: "Ameer",
        totalPrice: 200.0
    } 
]
app.get('/orders',(req,res)=>{
    res.json(orders);
})
app.get('/orders/:id',(req,res)=>{
    const Id=parseInt(req.params.id);
    const order=orders.find(u=> u.id===Id)
    if(!order){
        res.status(404).json("User Not Found")
    }
    res.send(order)
})
app.post('/orders',(req,res)=>{
    const {customerName,totalPrice}=req.body;
    if(!customerName){
        return res.status(400).json({message: "Name is required"})
    }
    const newOrder={
        id:orders.length+1,
        customerName: customerName,
        totalPrice: totalPrice
    }
    orders.push(newOrder)
    res.status(201).send(newOrder)
})
app.put('/orders/:id', (req, res) => {
    const orderId = parseInt(req.params.id);
    const { customerName, totalPrice } = req.body;
    const orderIndex = orders.findIndex(order => order.id === orderId);
    if (orderIndex === -1) {
        return res.status(404).json({ message: "Order not found" });
    }

    if (!customerName) {
        return res.status(400).json({ message: "Customer name is required" });
    }
    if (totalPrice == null || isNaN(totalPrice) || totalPrice < 0) {
        return res.status(400).json({ message: "Total price must be a valid non-negative number" });
    }
    orders[orderIndex] = {
        id: orderId,
        customerName,
        totalPrice
    };

    res.status(200).json(orders[orderIndex]);
});
app.delete('/orders/:id',(req,res)=>{
    const Id=req.params.id;
    orders=orders.filter(u=>u.id!=Id)
    res.status(200).send();
})
app.listen(PORT,()=>{
    console.log(`App is listening at PORT ${PORT}`)
})

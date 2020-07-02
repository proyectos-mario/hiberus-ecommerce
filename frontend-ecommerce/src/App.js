import React, { useState, useEffect } from "react";
import './App.css';


const executeCheckOutAPI = async (formOut) => {
  const response = await fetch('http://localhost:7002/api/checkout', {
    method: "post",
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(formOut)
  });

  return [response, response.status];

}

const getClientsAPI = async () => {

  const response = await fetch('http://localhost:7002/api/getClients', {
    method: "get",
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    }
  });

  return [response, response.status];

}


const getProductsAPI = async () => {

  const response = await fetch('http://localhost:7000/api/getProducts', {
    method: "get",
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    }
  });

  return [response, response.status];

}

const getOrdersAPI = async () => {

  const response = await fetch('http://localhost:7001/api/getOrders', {
    method: "get",
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    }
  });

  return [response, response.status];

}




function App() {
  const [clients, setClients] = useState([])
  const [products, setProducts] = useState([]);
  const [orders, setOrders] = useState([]);
  const [resultCHO, setResultCHO] = useState({});
  const [formRegister, setFormRegister] = useState({
    "products": {}
  });

  const onSubmitForm = e => {
    e.preventDefault();

    const prod = formRegister.products;
    console.log('Prod',prod);
    
    // iterate through key-value gracefully
    let max = 0;
    for (var [key, value] of Object.entries(prod)) {

      let val = parseInt(key.substr(7, 2));
      if (val > max) {
        max = val;
      }

    }

    console.log('max', max); // "a 5", "b 7", "c 9"

    let productsArray = [];
    for (let i = 1; i <= max; i++) {
      if (prod.hasOwnProperty('product' + i) && prod['product' + i] === true) {
        let value = prod['textVal' + i];
        let quan = prod['textQuan' + i];
        if (value === undefined || quan === undefined) {
          alert('The product' + i + ", should to have cost and quantity");
          return;
        }
        let prodO = {
          "id": i,
          "quantity": quan,
          "cost": value
        }
        productsArray.push(prodO);
      }
    }
    console.log("productsArray", productsArray);
    if(productsArray===undefined || productsArray.length===0||!productsArray){
      alert('You are not selected any product ');
      return;
    }
    const formOut = { ...formRegister };

    formOut.products = productsArray;
    console.log('formOut', formOut);

    executeCheckOutAPI(formOut)
      .then(([response, status]) => {
        return response.json();
      })
      .then((responseCHO) => {
        setResultCHO(responseCHO)
        getOrdersAPI()
          .then(([response, status]) => {
            return response.json();
          })
          .then((responseOrder) => {
            setOrders(responseOrder)
          });
      });




  }

  const onChangeInput = e => {
    if (e.target.type === "checkbox") {
      setFormRegister({ ...formRegister, "products": { ...formRegister.products, [e.target.id]: e.target.checked } });

    } else if (e.target.type === "number") {
      setFormRegister({ ...formRegister, "products": { ...formRegister.products, [e.target.id]: e.target.value } });
    }
    else {
      setFormRegister({ ...formRegister, [e.target.id]: e.target.value });
    }
  }

  useEffect(() => {
    getClientsAPI()
      .then(([response, status]) => {
        return response.json();
      })
      .then((responseClient) => {
        setClients(responseClient)
      });
  }, []);

  useEffect(() => {
    getProductsAPI()
      .then(([response, status]) => {
        return response.json();
      })
      .then((responseProduct) => {
        setProducts(responseProduct)
      });
  }, []);

  useEffect(() => {
    getOrdersAPI()
      .then(([response, status]) => {
        return response.json();
      })
      .then((responseOrder) => {
        setOrders(responseOrder)
      });
  }, []);

  return (
    (clients.length === 0 || products.length === 0) ? <h1>Loading....</h1> :
      <form className="App" onSubmit={onSubmitForm}>
        <header className="header">
          <h1>Test page for e-commerce simulate</h1>
        </header>
        <section className="clientId">
          <h1>Client</h1>
          <select onChange={onChangeInput}
            className="selectItem" id='clientId'
            required
          >
            <option value="">
              --Seleccione un elemento--
                                    </option>
            {
              clients.map((data, i) =>
                <option value={data.personaId} key={i}>{data.personaId + "--" + data.name + " " + data.lastName}</option>)
            }

          </select>
        </section>
        <section className="products" onChange={onChangeInput}>
          <h1>Products</h1>

          {
            products.map((data, i) =>
              <div key={"infoProd" + i} className="infoProducts">
                <div className="elementProduct">
                  <input type="checkbox" id={"product" + data.productId} onChange={onChangeInput} />
                  <label htmlFor={"product" + data.productId}>{data.productId + "-" + data.name}</label>
                </div>
                <input className="elementProduct" type="number" placeholder="Cost" id={"textVal" + data.productId} onChange={onChangeInput} />
                <input className="elementProduct" type="number" placeholder="Quantity" id={"textQuan" + data.productId} onChange={onChangeInput} />
              </div>
            )
          }

        </section>
        <section className="otherinfo">
          <h1>Aditional information</h1>

          <div className="otherInfoData">
            <label htmlFor="date">Date:</label>
            <input type="date" id="date" onChange={onChangeInput} required />
          </div>
          <div className="otherInfoData">
            <label htmlFor="direction">Adress:</label>
            <input type="text" placeholder="Address" id="direction" onChange={onChangeInput} required />
          </div>

        </section>
        <section className="actions">
          <h1>Actions</h1>
          <button className="execute">Execute checkOut Service</button>
        </section>
        {
          orders.length !== 0 && <section className="historyOrders">
            <h1>History orders</h1>
            <table className="orders">
              <thead>
                <tr>
                  <th><h2>OrderId</h2></th>
                  <th><h2>ClientId</h2></th>
                  <th><h2>Address</h2></th>
                  <th><h2>Sum</h2></th>
                  <th><h2>Date order</h2></th>
                  <th><h2>Date generation</h2></th>
                </tr>
              </thead>
              <tbody>

                {
                  orders.map((data, i) =>
                    <tr id={"row" + i}>

                      <td><h3 id={"order" + i}>{data.orderId}</h3></td>
                      <td><h3 id={"client" + i}>{data.clientId}</h3></td>
                      <td><h3 id={"address" + i}>{data.address}</h3></td>
                      <td><h3 id={"sum" + i}>{data.productTotal}</h3></td>
                      <td><h3 id={"order" + i}>{data.dateOrder}</h3></td>
                      <td><h3 id={"generation" + i}>{data.dateGeneration}</h3></td>
                    </tr>
                  )}


              </tbody>
            </table>
          </section>
        }


      </form>
  );
}

export default App;

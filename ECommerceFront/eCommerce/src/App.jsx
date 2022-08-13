
import {Navbar} from "./components/navbar/Navbar.jsx";
import {Product} from "./components/Product/Product.jsx";
import {Grid} from "@mui/material";
import {MainPage} from "./pages/MainPage";
import {Footer} from "./components/footer/Footer";
import {useEffect, useState} from "react";
import {fakeList} from "./FakeProducts.js";
import {RightBar} from "./components/rightBar/RightBar";



function App() {

    const [products, setProducts] = useState([]);

    useEffect(() => {
        setProducts(fakeList);
    },[]);

  return (
    <>
            <Navbar className = {"navbar"} />
        <Grid container spacing={3}>
        {products.map((item,index) => {
            return(
                <>
                <Grid item xs = {3} key={index}>
                <Product product = {item}/>
                </Grid>
                </>
            )
        })}
        </Grid>
        <Footer/>
    </>
  )

}

export default App

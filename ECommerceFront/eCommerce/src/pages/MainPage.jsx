import {Grid, Slide, Slider} from "@mui/material";
import {fakeList} from "../FakeProducts.js";
import {useEffect} from "react";
import {Product} from "../components/Product/Product.jsx";
import '../../public/images/slider/1.jpg'
export const MainPage = (props) => {
const images = [ "../../public/images/slider/1.jpg","../../public/images/slider/2.jpg","../../public/images/slider/3.jpg"]


    return (
        <Grid container spacing = {2}>
            <Grid item xs = {12}>
                Slider

            </Grid>

            <Grid item xs = {12}>
                <Grid container gridAutoColumns>
                    {fakeList.map((item,index) => {
                        return (
                            <Grid item xs = {4} key = {index} >
                                <Product product = {item}/>
                            </Grid>
                        )
                    })}
                </Grid>

            </Grid>


        </Grid>
    )
}
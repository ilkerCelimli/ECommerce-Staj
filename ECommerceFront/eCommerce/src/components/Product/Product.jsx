import {
    Alert,
    Avatar,
    Badge, Button,
    ButtonGroup,
    Card,
    CardContent,
    CardHeader,
    CardMedia, Grid,
    IconButton, Link,
    Typography
} from "@mui/material";
import {AddCard, MoreVertOutlined, PlusOne, Remove, ShoppingBag, ShoppingCart} from "@mui/icons-material";
import {useEffect, useState} from "react";
import AddIcon from '@mui/icons-material/Add';
import RemoveIcon from '@mui/icons-material/Remove';
import {useDispatch, useSelector} from "react-redux";
import {addToBasket, deletToBasket} from "../../store/BasketReducer.js";


    export const Product = ({product}) => {
        const auth = useSelector(state => state.auth);
        const reducer = useDispatch();

        const [itemCount, setItemCount] =useState(1);
        const [open, setOpen] = useState();
        const handleClickOpen = (e) => {
            setOpen(e.currentTarget);
        }
        const handleClose = () => {
            setOpen(null);
        }

        const handleDiscShopping = () => {
        reducer(deletToBasket(product));
        }
        const [a,setA] = useState(false);

        const addShoppingBasket = () => {
            if(auth.isAuthenticated) {
                product.quantity = itemCount;
                reducer(addToBasket(product));
                setA(false)
            }
            else {
                setA(!a)
            }
        }

        const incrementQuantityEvent = () => {
            setItemCount(itemCount + 1);
        }
        const decreaseQuantityEvent =() => {
            setItemCount(itemCount - 1);
        }

        return (
            <Card sx={{maxWidth: 350}}>
                <CardHeader action={
                    <IconButton onClick={handleClickOpen} aria-label={`${product.name}`}>
                        <MoreVertOutlined />
                    </IconButton>}
                            title={product.name}
                            subheader={`${product.price} TL`}/>
                <CardMedia component = {"img"} src={product.photo} alt = {product.title} height = {194} />

                <CardContent>
                    <Badge badgeContent={itemCount} color="secondary">
                        <ShoppingBag/>
                    </Badge>
                        <ButtonGroup>
                            <Button
                                onClick={decreaseQuantityEvent}
                            >
                                {" "}
                                <RemoveIcon fontSize="small" />
                            </Button>
                            <Button
                                onClick={incrementQuantityEvent}
                            >
                                {" "}
                                <AddIcon fontSize="small" />
                            </Button>

                            <Button onClick={addShoppingBasket}>
                                {"Ekle"}
                            </Button>
                        </ButtonGroup>
                   {/*     <Grid container spacing={1}>



                            <Grid item xs = {3}>

                                <IconButton onClick={incrementQuantityEvent}>
                                    <PlusOne/>
                                </IconButton>
                            </Grid>

                            <Grid item xs = {3}>
                                <IconButton onClick={decreaseQuantityEvent}>
                                    <Remove/>
                                </IconButton>
                            </Grid>

                            <Grid item xs = {3}>
                                <Typography component={'h2'} color="textPrimary" style={{marginTop:'7px'}}>
                                    {product.quantity}
                                </Typography>
                            </Grid>

                            <Grid item xs = {3}>
                                <IconButton aria-label={`${product.name}`}>
                                    <ShoppingCart/>
                                </IconButton>
                            </Grid>

                        </Grid>*/}
                        <Link href ={"/"} style = {{marginTop:'10px',marginLeft:'40px'}} >Bilgi</Link>

                </CardContent>

                {a && <Alert severity="error"> Önce giriş yapmalısınız. </Alert>}
            </Card>
        )


}
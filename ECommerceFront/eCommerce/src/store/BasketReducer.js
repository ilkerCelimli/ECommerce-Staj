import {createSlice} from "@reduxjs/toolkit";

const BasketReducer = createSlice({
  name: "basket",
  initialState: {
    basket: [],
      count: 0,
  },
    reducers: {
        addToBasket: (state, action) => {
            state.basket.push(action.payload);
            state.count++;
        },
        deleteToBasket: (state,action) => {
           if(state.basket.find(item => item.id === action.payload.id).quantity == 1){
               state.basket = state.basket.filter(item => item.id !== action.payload);
           }
           else {
                state.basket.find(item => item.id === action.payload.id).quantity--;
           }
           state.count--;
        }
    }});

export const {addToBasket,deletToBasket} = BasketReducer.actions;
export default BasketReducer.reducer;

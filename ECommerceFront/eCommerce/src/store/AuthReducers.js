import {createSlice} from "@reduxjs/toolkit";

const initialAuth = {
    isAuthenticated: false,
    user: {
        email:'',
        token:''
    }
}

export const AuthSlice = createSlice({
    name : 'auth',
    initialState : initialAuth,
    reducers : {
        login : (state, action) => {
            state.isAuthenticated = true;
            state.user = action.payload;
            localStorage.setItem('user', JSON.stringify(state.user));
        },
        logout:(state) => {
            state.isAuthenticated = false;
            state.user = {
                email:'',
                token:''
            }
            localStorage.removeItem('user');
        }
    }
})

export const {login, logout} = AuthSlice.actions;
export default AuthSlice.reducer;
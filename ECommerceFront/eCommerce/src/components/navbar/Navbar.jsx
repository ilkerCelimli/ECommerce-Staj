import {
    AppBar, Avatar,
    Box,
    Button,
    Container,
    Grid,
    IconButton,
    Menu,
    MenuItem, TextField,
    Toolbar,
    Tooltip,
    Typography
} from "@mui/material";
import MenuIcon from '@mui/icons-material/Menu';
import LoginIcon from '@mui/icons-material/Login';
import {useReducer, useState} from "react";
import {useDispatch, useSelector} from "react-redux";
import {useForm} from "react-hook-form";
import {Label} from "@mui/icons-material";
import {login, logout} from "../../store/AuthReducers.js";
import CloseIcon from '@mui/icons-material/Close';

export const Navbar = (props) => {

    const {handleChange,handleSubmit,register} = useForm();
    const auth = useSelector((state) => state.auth)
    const action = useDispatch();
    const basket = useSelector((state) => state.basket)

    const pages = ['Anasayfa', 'Ürünler', 'Hakkımızda', 'İletişim'];
    const [anchorElNav, setAnchorElNav] = useState(null);
    const [anchorElUser, setAnchorElUser] = useState(null);
    const [anchorElLogin,setAnchorElLogin] = useState(null);

    const onSubmit = (data) => {
        console.log(data)
        action(login(data))


    }

    const handleLogOut= () => {
        action(logout())
    }

    const handleLogin = (e) => {
        setAnchorElLogin(e.currentTarget);
    }
    const closeLogin = () => {
        setAnchorElLogin(null);
    }

    const handleOpenNavMenu = (event) => {
        setAnchorElNav(event.currentTarget);
    };
    const handleOpenUserMenu = (event) => {
        setAnchorElUser(event.currentTarget);
    };

    const handleCloseNavMenu = () => {
        setAnchorElNav(null);
    };

    const handleCloseUserMenu = () => {
        setAnchorElUser(null);
    };

    return (
        <AppBar position="static">
            <Container maxWidth="xl">
                <Toolbar disableGutters>
                    <Typography
                        variant="h6"
                        noWrap
                        component="a"
                        href="/"
                        sx={{
                            mr: 2,
                            display: {xs: 'none', md: 'flex'},
                            fontFamily: 'monospace',
                            fontWeight: 700,
                            letterSpacing: '.3rem',
                            color: 'inherit',
                            textDecoration: 'none',
                        }}
                    >
                        LOGO
                    </Typography>

                    <Box sx={{flexGrow: 1, display: {xs: 'flex', md: 'none'}}}>
                        <IconButton
                            size="large"
                            aria-label="account of current user"
                            aria-controls="menu-appbar"
                            aria-haspopup="true"
                            onClick={handleOpenNavMenu}
                            color="inherit"
                        >
                            <MenuIcon/>
                        </IconButton>
                        <Menu
                            id="menu-appbar"
                            anchorEl={anchorElNav}
                            anchorOrigin={{
                                vertical: 'bottom',
                                horizontal: 'left',
                            }}
                            keepMounted
                            transformOrigin={{
                                vertical: 'top',
                                horizontal: 'left',
                            }}
                            open={Boolean(anchorElNav)}
                            onClose={handleCloseNavMenu}
                            sx={{
                                display: {xs: 'block', md: 'none'},
                            }}
                        >
                            {pages.map((page) => (
                                <MenuItem key={page} onClick={handleCloseNavMenu}>
                                    <Typography textAlign="center">{page}</Typography>
                                </MenuItem>
                            ))}
                        </Menu>
                    </Box>
                    <Typography
                        variant="h5"
                        noWrap
                        component="a"
                        href=""
                        sx={{
                            mr: 2,
                            display: {xs: 'flex', md: 'none'},
                            flexGrow: 1,
                            fontFamily: 'monospace',
                            fontWeight: 700,
                            letterSpacing: '.3rem',
                            color: 'inherit',
                            textDecoration: 'none',
                        }}
                    >
                        LOGO
                    </Typography>
                    <Box sx={{flexGrow: 1, display: {xs: 'none', md: 'flex'}}}>
                        {pages.map((page) => (
                            <Button
                                key={page}
                                onClick={handleCloseNavMenu}
                                sx={{my: 2, color: 'white', display: 'block'}}
                            >
                                {page}
                            </Button>
                        ))}
                    </Box>

                    {auth.isAuthenticated ? (
                            <Box sx={{flexGrow: 0}}>
                                <Tooltip title="Open settings">
                                    <IconButton onClick={handleOpenUserMenu} sx={{p: 0}}>
                                        <Avatar alt="Remy Sharp" src="/static/images/avatar/2.jpg"/>
                                    </IconButton>
                                </Tooltip>
                                <Menu
                                    sx={{mt: '45px'}}
                                    id="menu-appbar"
                                    anchorEl={anchorElUser}
                                    anchorOrigin={{
                                        vertical: 'top',
                                        horizontal: 'right',
                                    }}
                                    keepMounted
                                    transformOrigin={{
                                        vertical: 'top',
                                        horizontal: 'right',
                                    }}
                                    open={Boolean(anchorElUser)}
                                    onClose={handleCloseUserMenu}
                                >


                                        <MenuItem onClick={handleCloseUserMenu}>
                                            <Typography textAlign="center">Profil</Typography>
                                        </MenuItem>

                                    <MenuItem onClick={handleCloseUserMenu}>
                                        <Typography textAlign="center">Sepetim{` ${basket.count}`}</Typography>
                                    </MenuItem>

                                    <MenuItem  onClick={handleCloseUserMenu}>
                                        <Typography textAlign="center">Çıkış yap</Typography>
                                    </MenuItem>



                                </Menu>
                            </Box>
                        ) :

                        (
                            <Box sx={{flexGrow: 0}}>
                                <IconButton
                                    size="large"
                                    aria-label="account of current user"
                                    aria-controls="menu-appbar"
                                    aria-haspopup="true"
                                    onClick={handleLogin}
                                    color="inherit"> <LoginIcon/> </IconButton>

                                <Menu
                                    sx={{mt: '45px'}}
                                    id="menu-appbar"
                                    anchorEl={anchorElLogin}
                                    anchorOrigin={{
                                        vertical: 'top',
                                        horizontal: 'right',
                                    }}
                                    keepMounted
                                    transformOrigin={{
                                        vertical: 'top',
                                        horizontal: 'right',
                                    }}
                                    open={Boolean(anchorElLogin)}
                                    onClose={closeLogin}>
                                    <Box component = {'form'} onSubmit={handleSubmit(onSubmit)} sx = {{  '& .MuiTextField-root': { m: 1, width: '25ch' },}} noValidate autoComplate={"off"}>

                                        <MenuItem onClick={closeLogin} style={{justifyContent:'flex-end'}}>
                                            <IconButton> <CloseIcon/> </IconButton>
                                        </MenuItem>

                                    <MenuItem>

                                        <TextField required id = {"outlined-required"} label = {"Kullanıcı Adı"} onChange={handleChange} {...register("email")}/>
                                    </MenuItem>
                                    <MenuItem>

                                        <TextField required id = {"outlined-required"} label = {"Şifre"} onChange={handleChange} {...register("password")}/>
                                    </MenuItem>

                                        <MenuItem>
                                            <Button type={"submit"} color={"primary"}>Giriş yap</Button>
                                        </MenuItem>

                                        <MenuItem>
                                            <Button>Kayıt ol</Button>
                                        </MenuItem>



                                    </Box>

                                </Menu>
                            </Box>
                        )
                    }
                </Toolbar>
            </Container>
        </AppBar>
    );
};
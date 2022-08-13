import {BottomNavigation, Grid} from "@mui/material";

import {
    Box,
    Container,
    Row,
    Column,
    FooterLink,
    Heading,
} from "./FooterStyle";
export const Footer = () => {

    return (
        <Grid container spacing = {2} style = {{position: 'fixed',bottom:-500,left:0}}>
            <Grid item xs = {12}>
        <Box>
            <h1 style={{ color: "green",
                textAlign: "center",
                marginTop: "-50px" }}>
                {"Mesleki Uyuglama-1 Staj"}
            </h1>
            <Container>
                <Row>
                    <Column>
                        <Heading>Hakkımızda</Heading>
                        <FooterLink href="#">Hedefimiz</FooterLink>
                        <FooterLink href="#">Vizyonumuz</FooterLink>
                    </Column>
                    <Column>
                        <Heading>ürünlerimiz</Heading>
                        <FooterLink href="#">Ürünler</FooterLink>
                    </Column>
                    <Column>
                        <Heading>İletişim</Heading>
                        <FooterLink href="#">Sayfa</FooterLink>
                    </Column>
                    <Column>
                        <Heading>Social Media</Heading>
                        <FooterLink href="#">
                            <i className="fab fa-facebook-f">
                <span style={{ marginLeft: "10px" }}>
                  Facebook
                </span>
                            </i>
                        </FooterLink>
                        <FooterLink href="#">
                            <i className="fab fa-instagram">
                <span style={{ marginLeft: "10px" }}>
                  Instagram
                </span>
                            </i>
                        </FooterLink>
                        <FooterLink href="#">
                            <i className="fab fa-twitter">
                <span style={{ marginLeft: "10px" }}>
                  Twitter
                </span>
                            </i>
                        </FooterLink>
                        <FooterLink href="#">
                            <i className="fab fa-youtube">
                <span style={{ marginLeft: "10px" }}>
                  Youtube
                </span>
                            </i>
                        </FooterLink>
                    </Column>
                </Row>
            </Container>
        </Box>
            </Grid>
        </Grid>
    );
}
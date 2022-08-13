import {useState} from "react";
import {ProSidebar, SidebarContent, SidebarFooter, SidebarHeader} from "react-pro-sidebar";

export const RightBar = () => {

    const [anchor,setAnchor] = useState();

    return( 
       <ProSidebar>
           <SidebarHeader>Kategoriler</SidebarHeader>

           <SidebarContent>ASDASD</SidebarContent>
           <SidebarContent>ASDASD</SidebarContent>
           <SidebarContent>ASDASD</SidebarContent>
           <SidebarContent>ASDASD</SidebarContent>

           <SidebarFooter>DSAFSAD</SidebarFooter>
       </ProSidebar>
    )
}
import {BrowserRouter, Routes, Route, Navigate,useNavigate, useParams, Link} from 'react-router-dom'
import './CSS/PostApp.css'
// import LogOutComponent from './LogoutComponent'
// import HeaderComponent from './HeaderComponent'
 import HomePage from './HomePage'
import ErrorComponent from './ErrorComponent'
import YourPostComponent from './YourPostComponent'
import LoginComponent from './LoginComponent'
import ViewAllPosts from './ViewAllPosts'
import HeaderComponent from './HeaderComponent'
import FooterComponent from './FooterComponent'
import LogOutComponent from './LogOutComponent'
import UpdatePost  from "./UpdatePost";
import AuthProvider, { useAuth } from './Security/AuthContext'

function AuthenticatedRoute({children}){
  const authContext = useAuth()
  if(authContext.isAuthenticated){
  return children
  }else{
    return <Navigate to="/"></Navigate>
  }
  
}
export default function PostApp(){
    return(
        <div className="PostApp">
          <AuthProvider>
            <BrowserRouter>
              <HeaderComponent/>
                <Routes>
                    <Route path='/' element={<LoginComponent />}></Route>
                    <Route path='/login' element={<LoginComponent />}></Route>
                    <Route path='/welcome/:username' element={
                          <AuthenticatedRoute>
                          <HomePage />
                          </AuthenticatedRoute>
                    }></Route>
                    <Route path="*" element={<ErrorComponent />}></Route>
                    <Route path="/posts" element={
                    <AuthenticatedRoute>
                      <YourPostComponent />
                    </AuthenticatedRoute>
                   }></Route>
                    <Route path="/posts/:id" element={
                    <AuthenticatedRoute>
                    <UpdatePost />
                    </AuthenticatedRoute>
                    }></Route>
                     <Route path="/logout" element={
                     <AuthenticatedRoute>
                     <LogOutComponent />
                     </AuthenticatedRoute>
                     }></Route>
                     <Route path="/allposts" element={<ViewAllPosts/>}>
                     </Route>
                </Routes>
               {/* <FooterComponent/>  */}
            </BrowserRouter>
         </AuthProvider>
        </div>
    )
}


import { useState } from 'react'

import {BrowserRouter, Routes, Route, useNavigate, useParams, Link} from 'react-router-dom'
import { AuthContext, useAuth } from './Security/AuthContext'
function LoginComponent(){
    const[username, setUsername ]= useState('Udani')
    const[password, setPassword ] = useState('****')

    console.log(username)
    const[showSuccessMesaage,setShowSuccessMessage]= useState(false)
    const[showErrorMessage,setShowErrorMessage]= useState(false)

    const navigate = useNavigate();

    const authContext = useAuth();

    function handleUsernameChange(event){
        console.log(event);
        setUsername(event.target.value);

    }

       function handlePasswordChange(event){
        console.log(event);
        setPassword(event.target.value);

    }

    function handleSubmit(){
        if(authContext.login(username,password)){
            navigate(`/welcome/${username}`)
        }else{
            console.log('Failed')
            
            setShowErrorMessage(true)
        }
    }

    function SucessMessageComponent(){
    if(showSuccessMesaage){
    return(
        <div className='successMessage'>Authenticated Successfully</div>
    )
    }
    return null
}


    function ErrorMessageComponent(){
    if(showErrorMessage){
    return(
       <div className="errorMessage">Authentication Failed.Please check your credentials</div>
        
    )
    }
    return null
}

    return(
       
        <div className="Login">
                        <h1>Time to Login</h1>
                 {showErrorMessage && <div className='errorMessage'>Authenticaion Failed. Please Check your credentials</div>}
                <div className="LoginForm">
                <div>
                <label>User Name</label>
                <input type="text" name="username" value={username} onChange = {handleUsernameChange}/>
                </div>
    
                <div>
                <label>Password</label>
                <input type="password" name="password" value={password}  onChange = {handlePasswordChange}/>
                </div>

                <div>
                    <button type="button" name="login" onClick={handleSubmit}>Login</button>
                </div>

            </div>
              
        </div>

    )
}

export default LoginComponent
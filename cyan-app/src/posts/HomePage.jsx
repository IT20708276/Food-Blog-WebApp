import {BrowserRouter, Routes, Route, useNavigate, useParams, Link} from 'react-router-dom'
import { useState } from 'react';
function HomePage(){
    const {username} = useParams();

    const [message,setMessage] = useState(null)
 
 
    function successfulRespons(response){
        console.log(response)
        
       setMessage(response.data.message)
    }

     function errorResponse(error){
        console.log(error)
    }

    return(
        <div className="Welcome">
        <h1>Welcome {username}</h1>
            <div>
            Manage your todos -<Link to="/posts">Go here</Link>
            </div>
            
        </div>
    )
}

export default HomePage
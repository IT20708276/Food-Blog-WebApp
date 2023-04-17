import { useContext } from 'react'
import {Link} from 'react-router-dom'
import { AuthContext } from './Security/AuthContext'

function FooterComponent(){
    
     const authContext = useContext(AuthContext)
    console.log(`Footer component - ${authContext.number}`);
    return(
       <footer className='footer'>
        <div className='container'>
            Footer<hr/>
        </div>
           
       </footer>
    )
}

export default FooterComponent
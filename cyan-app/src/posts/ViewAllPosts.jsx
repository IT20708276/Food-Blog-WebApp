import { retrieveAllPosts } from "./PostApi/PostApi";
import { useEffect,useState } from "react";
import { useAuth } from "./Security/AuthContext";

function AllPosts(){
    const authContext = useAuth()
    console.log('inside View All Posts')
     const [result,setResult] = useState([])
    useEffect(
        () => {
            refreshAllPosts()
        },[]
    )

    function refreshAllPosts(){
        retrieveAllPosts()
        .then(response=>{
            setResult(response.data)
        }).catch(error =>console.error())
    }

    return(
        <div className="container">
            <h1>Find Out What others Post!!</h1>
            <div>
                <table className='table'>
                    <thead>
                          <tr>
                            <th>Username</th>
                            <th>Location</th>
                            <th>Image</th>
                            <th>Caption</th>
                            <th>Memories From</th>
                        </tr>
                    </thead>
                    <tbody>
                     
                            {
                            result.map(
                                post => (
                                <tr key = {post.postNumber}>
                            <td>{post.userName}</td>
                            <td>{post.location}</td>
                            <td>{post.imageFile}</td>
                            <td>{post.description}</td>
                            <td>{post.targetDate.toString()}</td> 
                           </tr>
                                )
                            )
                        }  
                        
                    </tbody>
                </table>

            </div>
        </div>

    )
}

export default AllPosts
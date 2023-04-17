import { useEffect, useState } from "react";
import { retrieveAllPostsForUsername } from "./PostApi/PostApi.js";
import { deletePostsApi } from "./PostApi/PostApi.js";
import { useAuth } from "./Security/AuthContext.js";
import { useNavigate } from "react-router-dom";
import socialmedia from "./FoodBowl.jpg";
import "./CSS/PostApp.css";
function YourPosts() {
  const authContext = useAuth();

  const username = authContext.username;

  //    const today = new Date();
  //     const uploadedDate = new Date(today.getFullYear()+12, today.getMonth(), today.getDay())

  const navigate = useNavigate();
  const [posts, setPosts] = useState([]);
  const [message, setMessage] = useState([]);
  //  const posts = [
  //     // {id: 1, location : 'Learn AWS',ImageFile: 'NULL', uploadedDate: uploadedDate},
  //     // {id: 2, location : 'Learn AWS',ImageFile: 'NULL', uploadedDate: uploadedDate},
  //     // {id: 3, location : 'Learn AWS',ImageFile: 'NULL', uploadedDate: uploadedDate},
  //  ]

  useEffect(() => {
    refreshPosts();
  }, []);
  function refreshPosts() {
    retrieveAllPostsForUsername(username)
      .then((response) => {
        setPosts(response.data);
        // let dataUrl = URL.createObjectURL(blob);
      })
      .catch((error) => console.log(error));
  }

  function deletePosts(id) {
    deletePostsApi(username, id)
      .then(() => {
        setMessage(`Your Post was deleted `);
        refreshPosts();
      })
      .catch((error) => console.log(error));
  }

  function updatePost(id) {
    navigate(`/posts/${id}`);
  }

  function addNewPost() {
    navigate(`/posts/-1`);
  }
  return (
    <div className="container">
      <div className="addPostbtn">
        <h1>Your Posts</h1>
        <div className="btn btn-success m-5" onClick={addNewPost}>
          Add New Post
        </div>
      </div>
      {message && <div className="alert alert-warning">{message}</div>}

      <div>
        <table className="table" class="d-flex justify-content-center">
          <tbody>
            {posts.map((post) => (
              <tr key={post.postNumber}>
                <tr
                  style={{
                    backgroundColor: "#ced4da",
                  }}
                >
                  <tr>
                    <td
                      style={{
                        height: "100%",
                        width: "500px",
                        textAlign: "left",
                        paddingLeft: "10px",
                      }}
                    >
                      {post.userName}
                    </td>
                    <td
                      style={{
                        padding: "10px",
                      }}
                    >
                      <button
                        className="btn btn-warning"
                        onClick={() => deletePosts(post.postNumber)}
                      >
                        Delete
                      </button>
                    </td>
                  </tr>
                  <tr>
                    <td
                      style={{
                        height: "100%",
                        width: "500px",
                        textAlign: "left",
                        paddingTop: "none",
                        paddingLeft: "10px",
                      }}
                    >
                      {post.location}
                    </td>
                    <td
                      style={{
                        padding: "10px",
                      }}
                    >
                      <button
                        className="btn btn-success"
                        onClick={() => updatePost(post.postNumber)}
                      >
                        Update
                      </button>
                    </td>
                  </tr>
                </tr>
                <tr>
                  <td>{post.ImageFile}</td>
                  <td
                    style={{
                      height: "20px",
                    }}
                  ></td>
                </tr>
                <tr>
                  <td
                    style={{
                      height: "50px",
                      paddingLeft: "10px",
                      width: "150px",
                      textAlign: "left",
                    }}
                  >
                    {post.description}
                  </td>
                  <td>{post.targetDate.toString()}</td>
                </tr>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}
export default YourPosts;

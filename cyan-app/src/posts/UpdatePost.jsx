import {
  BrowserRouter,
  Routes,
  Route,
  useNavigate,
  useParams,
  Link,
} from "react-router-dom";
import {
  retrievePostsApi,
  updatePostsApi,
  createPostApi,
} from "./PostApi/PostApi";
import { useAuth } from "./Security/AuthContext";
import moment from "moment";
import { ChangeEvent, useEffect, useState } from "react";
import { Formik, Form, Field, ErrorMessage } from "formik";

export default function UpdatePost() {
  const { id } = useParams();

  const [description, setDescription] = useState("");
  const [targetDate, setTargetDate] = useState("");
  const [location, setLocation] = useState("");
  const [imageFile, setImageFile] = useState("");

  const authContext = useAuth();
  const username = authContext.username;
  const navigate = useNavigate();
  useEffect(() => retrievePost(), [id]);

  //Function to Upload
  const [file, setFile] = useState(null);

  const handleFileUpload = (e) => {
    const reader = new FileReader();
    const file = e.target.files[0];
    setFile(file);
    var g = reader.readAsDataURL(file);
    console.log(g);
  };

  //retrieve Post Function
  function retrievePost() {
    if (id != -1) {
      retrievePostsApi(username, id)
        .then((response) => {
          setDescription(response.data.description);
          setTargetDate(response.data.targetDate);
          setLocation(response.data.location);
          setImageFile(response.data.imageFile);
        })
        .catch((error) => console.log(error));
    }
  }
  //Add a Post Button Click
  function onSubmit(values) {
    const post = {
      userName: username,
      PostNumber: "NULL",
      location: values.location,
      imageFile: values.file,
      description: values.description,
      targetDate: values.targetDate,
    };
    console.log("Inside on submit values " + post);
    if (id == -1) {
      console.log("now id is -1");

      createPostApi(username, post)
        .then((response) => {
          navigate("/posts");
        })
        .catch((error) => console.log(error));
    } else {
      console.log("now id is not -1");
      updatePostsApi(username, id, post)
        .then((response) => {
          navigate("/posts");
        })
        .catch((error) => console.log(error));
    }
  }

  function validate(values) {
    let errors = {};
    if (values.description.length > 400) {
      errors.description = "Your Caption is too lengthy";
    }

    if (!values.description) {
      errors.description = "Add a caption to Update";
    }

    if (!values.location) {
      errors.location = "Enter the dine-in location";
    }
    console.log("Values inside validate function" + values);
    return errors;
  }
  return (
    <div className="container">
      <h1>Post Details</h1>
      <div>
        <Formik
          initialValues={{ description, targetDate, location }}
          enableReinitialize={true}
          onSubmit={onSubmit}
          validate={validate}
          validateOnChange={false}
          validateOnBlur={false}
        >
          {(props) => (
            <Form>
              <ErrorMessage
                name="description"
                component="div"
                className="alert alert-warning"
              />

              <ErrorMessage
                name="targetDate"
                component="div"
                className="alert alert-warning"
              />

              <ErrorMessage
                name="location"
                component="div"
                className="alert alert-warning"
              />

              <fieldset className="form-group">
                <label>Description</label>
                <Field
                  type="text"
                  className="form-control"
                  name="description"
                />
              </fieldset>

              <fieldset className="form-group">
                <label>Memories From</label>
                <Field
                  type="date"
                  className="form-control"
                  name="targetDate"
                  max={moment().format("YYYY-MM-DD")}
                />
              </fieldset>
              <fieldset className="form-group">
                <input
                  type="file"
                  class="form-control-file"
                  onChange={handleFileUpload}
                  id="exampleFormControlFile1"
                  multiple
                />

                <button class="btn btn-dark">Upload</button>
              </fieldset>

              <fieldset className="form-group">
                <label>Location</label>
                <Field type="text" className="form-control" name="location" />
              </fieldset>

              <div>
                <button className="btn btn-success m-5" type="submit">
                  Save
                </button>
              </div>
            </Form>
          )}
        </Formik>
      </div>
    </div>
  );
}

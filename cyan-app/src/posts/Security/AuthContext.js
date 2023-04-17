import { createContext, useState, useContext } from "react";

//1.Create a Context
export const AuthContext = createContext();

export const useAuth = () => useContext(AuthContext);

//Share the created context with other components
export default function AuthProvider({ children }) {
  //Put somestate in the context
  const [isAuthenticated, setAuthenticated] = useState(false);
  const [username, setUsername] = useState(null);

  function logout() {
    setAuthenticated(false);
  }

  function login(username, password) {
    if (username === "the_social_toad" && password === "123") {
      setAuthenticated(true);
      setUsername(username);
      return true;
    } else {
      setAuthenticated(false);
      setUsername(null);
      return false;
    }
  }

  // const valueToBeShared = {isAuthenticated,setAuthenticated}
  return (
    <AuthContext.Provider
      value={{ isAuthenticated, setAuthenticated, login, logout, username }}
    >
      {children}
    </AuthContext.Provider>
  );
}

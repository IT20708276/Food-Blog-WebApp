import axios from "axios";

const apiClient = axios.create({
  baseURL: "http://localhost:8089",
});

export const retrieveAllPostsForUsername = (username) =>
  apiClient.get(`/users/${username}/posts`);

export const retrieveAllPosts = () => apiClient.get(`/users/posts`);

export const deletePostsApi = (username, number) =>
  apiClient.delete(`/users/${username}/posts/${number}`);

export const retrievePostsApi = (username, number) =>
  apiClient.get(`/users/${username}/posts/${number}`);

export const updatePostsApi = (username, number, post) =>
  apiClient.put(`/users/${username}/posts/${number}`, post);

export const createPostApi = (username, post) =>
  apiClient.post(`/users/${username}/posts`, post);

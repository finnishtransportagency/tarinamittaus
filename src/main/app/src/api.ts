import { fullRestURL } from "./App";

const baseUrl = fullRestURL();

export const postData = async (data = {}) => {
  const response = await fetch(baseUrl, {
    method: 'POST',
    mode: 'cors',
    cache: 'no-cache',
    credentials: 'same-origin',
    headers: {
      'Content-Type': 'application/json'
    },
    redirect: 'follow',
    referrerPolicy: 'no-referrer',
    body: JSON.stringify(data)
  });
  return response.json();
}

export const putData = async (data = {}) => {
  const response = await fetch(baseUrl, {
    method: 'PUT',
    mode: 'cors',
    cache: 'no-cache',
    credentials: 'same-origin',
    headers: {
      'Content-Type': 'application/json'
    },
    redirect: 'follow',
    referrerPolicy: 'no-referrer',
    body: JSON.stringify(data)
  });
  return response.json();
}

export const getData = async (id: string) => {
  return fetch(baseUrl + id, {
    method: 'GET',
    mode: 'cors',
    cache: 'no-cache',
    credentials: 'same-origin',
    headers: {
      'Content-Type': 'application/json',
    },
    redirect: 'follow',
    referrerPolicy: 'no-referrer',
  }).then(data => data.json())
    .then(json => json.status && json.status !== 200 ? null : json)
    .catch(err => {
      console.error(err);
      return null;
    });
}

export const deleteData = async (id: string) => {
  const response = await fetch(baseUrl + id, {
    method: 'DELETE',
    mode: 'cors',
    cache: 'no-cache',
    credentials: 'same-origin',
    headers: {
      'Content-Type': 'application/json',
    },
    redirect: 'follow',
    referrerPolicy: 'no-referrer',
  });
  return response.json();
}
import axios from 'axios';

const findAll = () =>
  axios.get('/api/v1/admin/module', response => {
    console.log(response);
  });

export { findAll };

const API_URL = 'http://192.168.0.103:8080/f1buzz/api/v1/user';

export const loginUser = async (username, password) => {
  try {
    const response = await fetch(`${API_URL}/login`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': 'Basic ' + btoa('admin:admin123'),
      },
      body: JSON.stringify({ username, password }),
    });

    const text = await response.text();
    console.log(text);
    const data = text ? JSON.parse(text) : null;

    if (!response.ok) {
      throw new Error(data?.message || 'Login failed');
    }

    return data;
  } catch (error) {
    console.log('Error during login:', error.message);
    throw error;
  }
};

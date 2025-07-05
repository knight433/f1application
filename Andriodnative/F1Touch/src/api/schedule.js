const API_URL = 'http://192.168.0.103:8080/f1app/api/v1/schedual/get/all';

export const getRaces = async () => {
  try {
    const response = await fetch(API_URL, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': 'Basic ' + btoa('admin:admin123'),
      },
    });

    const text = await response.text();
    console.log('Schedule API response got it');
    const data = text ? JSON.parse(text) : null;

    if (!response.ok) {
      throw new Error(data?.message || 'Failed to fetch races');
    }

    return data;
  } catch (error) {
    console.log('Error fetching races:', error.message);
    throw error;
  }
};

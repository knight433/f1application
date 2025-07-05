import React, { useState } from 'react';
import { View, Text, Button, StyleSheet, Alert } from 'react-native';
import CustomInput from '../components/CustomInput';
import { useNavigation } from '@react-navigation/native';
import { loginUser } from '../api/auth';

const LoginScreen = () => {
  const navigation = useNavigation();

  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const handleLogin = async () => {
    try {
      const data = await loginUser(username, password);
      navigation.navigate('Landing');

    } catch (error) {
      Alert.alert('Login Failed', error.message);
    }
  };

return (
  <View style={styles.container}>
    <Text style={styles.title}>Login</Text>
    <CustomInput placeholder="Username" value={username} setValue={setUsername} />
    <CustomInput placeholder="Password" value={password} setValue={setPassword} secureTextEntry />
    <View style={styles.button}>
      <Button title="Login" onPress={handleLogin} />
    </View>
  </View>
);
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    paddingTop: 100,
    alignItems: 'center',
    backgroundColor: '#fff',
  },
  title: {
    fontSize: 32,
    marginBottom: 20,
  },
  button: {
    marginTop: 20,
    width: '80%',
  },
});

export default LoginScreen;

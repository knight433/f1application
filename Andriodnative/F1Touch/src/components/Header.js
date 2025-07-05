import React from 'react';
import { View, Text, StyleSheet, TouchableOpacity } from 'react-native';
import Icon from 'react-native-vector-icons/FontAwesome';

const Header = ({ isLogin, setIsLogin, navigation }) => {
  return (
    <View style={styles.headerContainer}>
      <Text style={styles.headerTitle}>F1Touch</Text>
      <View style={styles.headerButtonWrapper}>
        <TouchableOpacity
          style={styles.loginButton}
          onPress={() => {
            if (isLogin) setIsLogin(false);
            else navigation.navigate('Login');
          }}
        >
          <Icon name={isLogin ? 'sign-out' : 'sign-in'} size={16} color="#fff" style={{ marginRight: 6 }} />
          <Text style={styles.loginButtonText}>{isLogin ? 'Logout' : 'Login'}</Text>
        </TouchableOpacity>
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  headerContainer: {
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'space-between',
    backgroundColor: '#d32d3c',
    borderRadius: 20,
    marginTop: 16,
    marginHorizontal: 8,
    paddingVertical: 18,
    paddingHorizontal: 18,
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 4 },
    shadowOpacity: 0.18,
    shadowRadius: 8,
    elevation: 8,
  },
  headerTitle: {
    fontSize: 28,
    color: 'black',
    letterSpacing: 1,
    fontFamily: 'Orbitron' 
  },
  headerButtonWrapper: {
    alignItems: 'center',
    justifyContent: 'center',
  },
  loginButton: {
    flexDirection: 'row',
    alignItems: 'center',
    backgroundColor: '#181818',
    borderRadius: 20,
    paddingVertical: 6,
    paddingHorizontal: 16,
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 2 },
    shadowOpacity: 0.12,
    shadowRadius: 4,
    elevation: 2,
  },
  loginButtonText: {
    color: '#fff',
    fontWeight: 'bold',
    fontSize: 16,
    letterSpacing: 0.5,
    fontFamily: 'Orbitron',
  },
});

export default Header;

import React from 'react';
import { View, TouchableOpacity, StyleSheet } from 'react-native';
import Icon from 'react-native-vector-icons/FontAwesome';

const Footer = ({ onPress1, onPress2, onPress3, onPress4 }) => {
  return (
    <View style={styles.footerContainer}>
      <TouchableOpacity onPress={onPress1} style={styles.iconButton}>
        <Icon name="home" size={28} color="#181818" />
      </TouchableOpacity>
      <TouchableOpacity onPress={onPress2} style={styles.iconButton}>
        <Icon name="bar-chart" size={28} color="#181818" />
      </TouchableOpacity>
      <TouchableOpacity onPress={onPress3} style={styles.iconButton}>
        <Icon name="book" size={28} color="#181818" />
      </TouchableOpacity>
      <TouchableOpacity onPress={onPress4} style={styles.iconButton}>
        <Icon name="ellipsis-h" size={28} color="#181818" />
      </TouchableOpacity>
    </View>
  );
};

const styles = StyleSheet.create({
  footerContainer: {
    flexDirection: 'row',
    justifyContent: 'space-around',
    alignItems: 'center',
    paddingVertical: 10,
    backgroundColor: '#d32d3c', // Red background
    borderTopLeftRadius: 24,
    borderTopRightRadius: 24,
    width: '100%',
    position: 'absolute',
    bottom: 0,
    elevation: 8,
    shadowColor: '#000',
    shadowOffset: { width: 0, height: -2 },
    shadowOpacity: 0.08,
    shadowRadius: 4,
    marginTop: 12,
  },
  iconButton: {
    padding: 8,
  },
});

export default Footer;
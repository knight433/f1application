import { View, Text, StyleSheet } from 'react-native';

const RaceCard = ({ trackName, fullName, nextSession }) => {
  return (
    <View style={styles.card}>
      <Text style={styles.raceName}>{trackName}</Text>
      <Text style={styles.circuitName}>{fullName}</Text>
      <Text style={styles.nextSession}>{nextSession}</Text>
    </View>
  );
};

const styles = StyleSheet.create({
  card: {
    width: '100%',
    height: 180,
    backgroundColor: '#d32d3c',
    borderRadius: 32,
    padding: 8,
    alignSelf: 'center',
    justifyContent: 'center',
    alignItems: 'center',
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 4 },
    shadowOpacity: 0.18,
    shadowRadius: 8,
    elevation: 6,
    marginVertical: 8,
  },
  raceName: {
    fontSize: 28,
    textAlign: 'center',
    marginBottom: 8,
    color: '#fff',
    fontFamily: 'Orbitron',
    letterSpacing: 1,
  },
  circuitName: {
    fontSize: 16,
    fontWeight: '500',
    marginBottom: 12,
    textAlign: 'center',
    color: '#fff',
    fontFamily: 'Orbitron',
    fontStyle: 'normal',
  },
  nextSession: {
    color: 'black',
    fontSize: 14,
    textAlign: 'center',
    fontWeight: '600',
    backgroundColor: 'rgba(0,0,0,0.10)',
    paddingHorizontal: 12,
    paddingVertical: 8,
    borderRadius: 10,
    overflow: 'hidden',
    fontFamily: 'Orbitron',
    marginTop: 8,
  },
});

export default RaceCard;

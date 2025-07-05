import React, { useState, useEffect } from 'react';
import { View, Text, ActivityIndicator, StyleSheet, ScrollView } from 'react-native';
import { useNavigation } from '@react-navigation/native';
import Header from '../components/Header';
import RaceCardSlids from "../components/RaceCardSlids";
import { getRaces } from "../api/schedule";
import Footer from '../components/Footer';

const LandingScreen = () => {
  const navigation = useNavigation();
  const [races, setRaces] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [isLogin, setIsLogin] = useState(false)

  useEffect(() => {
    fetchRaces();
  }, []);

  const fetchRaces = async () => {
    try {
      setLoading(true);
      const racesData = await getRaces();
      const transformedData = racesData.map(race => ({
        trackName: race.raceName,
        fullName: race.circutName,
        nextSession: formatNextSession(race),
        raceData: race 
      }));
      setRaces(transformedData);
      setError(null);
    } catch (err) {
      console.error('Error fetching races:', err);
      setError('Failed to load races');
    } finally {
      setLoading(false);
    }
  };

  const formatNextSession = (race) => {
    const now = new Date();
    const raceTime = new Date(race.raceTime);
    const qualiTime = new Date(race.qualiTime);
    const fp3Time = new Date(race.sessionThreeTime);
    const fp2Time = new Date(race.sessionTwoTime);
    const fp1Time = new Date(race.sessionOneTime);
    let sessions = [];
    if (race.typeOfWeekend === "GP") {
      sessions = [
        { name: 'FP1', time: fp1Time },
        { name: 'FP2', time: fp2Time },
        { name: 'FP3', time: fp3Time },
        { name: 'Qualifying', time: qualiTime },
        { name: 'Race', time: raceTime }
      ];
    } else {
      sessions = [
        { name: 'FP1', time: fp1Time },
        { name: 'Sprint Qualifying', time: fp2Time },
        { name: 'Sprint', time: fp3Time },
        { name: 'Qualifying', time: qualiTime },
        { name: 'Race', time: raceTime }
      ];
    }
    const nextSession = sessions.find(session => session.time > now);
    if (nextSession) {
      const date = nextSession.time.toLocaleDateString('en-US', { 
        weekday: 'short', 
        month: 'short', 
        day: 'numeric' 
      });
      const time = nextSession.time.toLocaleTimeString('en-US', { 
        hour: '2-digit', 
        minute: '2-digit',
        hour12: false 
      });
      return `${nextSession.name} - ${date} ${time}`;
    }
    return 'Completed';
  };

  const getNextSessionIndex = (races) => {
    const now = new Date();
    return races.findIndex(race => {
      const raceData = race.raceData || race;
      const times = [
        new Date(raceData.sessionOneTime),
        new Date(raceData.sessionTwoTime),
        new Date(raceData.sessionThreeTime),
        new Date(raceData.qualiTime),
        new Date(raceData.raceTime)
      ];
      return times.some(time => time > now);
    });
  };

  const nextSessionIndex = getNextSessionIndex(races);

  if (loading) {
    return (
      <View style={styles.centerContainer}>
        <ActivityIndicator size="large" color="#d32d3c" />
        <Text style={styles.loadingText}>Loading races...</Text>
      </View>
    );
  }

  if (error) {
    return (
      <View style={styles.centerContainer}>
        <Text style={styles.errorText}>{error}</Text>
      </View>
    );
  }

  return (
    <View style={styles.root}>
      <Header isLogin={isLogin} setIsLogin={setIsLogin} navigation={navigation} />
      <RaceCardSlids cardsData={races} initialIndex={nextSessionIndex} />
      <View style={styles.divider} />
      <Text style={styles.newsTitle}>News</Text>
      <Footer
        onPress1={() => console.log('Footer button 1 pressed')}
        onPress2={() => console.log('Footer button 2 pressed')}
        onPress3={() => console.log('Footer button 3 pressed')}
        onPress4={() => console.log('Footer button 4 pressed')}
      />
    </View>
  );
};

const styles = StyleSheet.create({
  root: {
    flex: 1,
    backgroundColor: '#292929',
    justifyContent: 'flex-start',
  },
  centerContainer: {
    flex: 1,
    backgroundColor: '#292929',
    justifyContent: 'center',
    alignItems: 'center',
  },
  loadingText: {
    marginTop: 10,
    fontSize: 16,
    color: '#fff',
    fontFamily: 'Orbitron',
  },
  errorText: {
    fontSize: 16,
    color: '#d32d3c',
    textAlign: 'center',
    fontFamily: 'Orbitron',
  },
  divider: {
    height: 5,
    marginVertical: 18,
    borderRadius: 8,
    backgroundColor: 'rgba(255,255,255,0.25)',
    shadowColor: '#fff',
    shadowOffset: { width: 0, height: 2 },
    shadowOpacity: 0.25,
    shadowRadius: 8,
    marginHorizontal: 24,
  },
  newsTitle: {
    color: '#fff',
    fontSize: 28,
    fontFamily: 'Orbitron',
    marginLeft: 16,
    marginTop: 12,
    marginBottom: 8,
    letterSpacing: 1,
  },
});

export default LandingScreen;

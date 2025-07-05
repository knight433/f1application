import React, { useRef, useEffect } from 'react';
import { ScrollView, View, StyleSheet, Dimensions } from 'react-native';
import RaceCard from './RaceCard';

const CARD_WIDTH = 260;
const SIDE_SPACING = 20;
const SCREEN_WIDTH = Dimensions.get('window').width;
const SPACER_WIDTH = (SCREEN_WIDTH - CARD_WIDTH) / 2;

const RaceCardSlids = ({ cardsData, initialIndex = 0 }) => {
  const scrollRef = useRef(null);

  const scrollToCard = (index, animated = true) => {
    const offset = index * (CARD_WIDTH + SIDE_SPACING);
    scrollRef.current?.scrollTo({ x: offset, animated });
  };

  useEffect(() => {
    if (cardsData.length > 0) {
      scrollToCard(initialIndex, false);
    }
  }, [cardsData]);

  const handleSnap = (event) => {
    const scrollX = event.nativeEvent.contentOffset.x;
    const index = Math.round(scrollX / (CARD_WIDTH + SIDE_SPACING));
    scrollToCard(index);
  };

  return (
    <ScrollView
      ref={scrollRef}
      horizontal
      showsHorizontalScrollIndicator={false}
      snapToInterval={CARD_WIDTH + SIDE_SPACING}
      decelerationRate="fast"
      onMomentumScrollEnd={handleSnap}
      contentContainerStyle={styles.scrollContainer}
    >
      <View style={{ width: SPACER_WIDTH }} />
      {cardsData.map((card, idx) => (
        <View key={idx} style={styles.cardWrapper}>
          <RaceCard
            trackName={card.trackName}
            fullName={card.fullName}
            nextSession={card.nextSession}
          />
        </View>
      ))}
      <View style={{ width: SPACER_WIDTH }} />
    </ScrollView>
  );
};

const styles = StyleSheet.create({
  scrollContainer: {
    paddingTop : 30
  },
  cardWrapper: {
    width: CARD_WIDTH,
    marginRight: SIDE_SPACING,
  },
});

export default RaceCardSlids;

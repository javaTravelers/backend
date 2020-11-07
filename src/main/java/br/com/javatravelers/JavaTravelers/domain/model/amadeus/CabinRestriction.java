package br.com.javatravelers.JavaTravelers.domain.model.amadeus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CabinRestriction {
	private String cabin; // [ ECONOMY, PREMIUM_ECONOMY, BUSINESS, FIRST ]
	private String coverage; // [ MOST_SEGMENTS, AT_LEAST_ONE_SEGMENT, ALL_SEGMENTS ]
	private String[] originDestinationIds;
}

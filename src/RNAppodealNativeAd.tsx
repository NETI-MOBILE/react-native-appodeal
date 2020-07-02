import React from 'react';
import { requireNativeComponent, StyleProp, ViewStyle } from 'react-native';


interface AppodealNativeAdProps {
	textColor?: string;
	textColorMore?: string;
	style?: StyleProp<ViewStyle>
}

const RNAppodealNativeView = requireNativeComponent<any>('RNAppodealNativeView');

const AppodealNativeAd = (props: AppodealNativeAdProps) => {
	const {
		textColor,
		textColorMore,
		style,
		...restProps
	} = props

	return (
		<RNAppodealNativeView
			textColor={textColor}
			textColorMore={textColorMore}
			style={[style]}
			{...restProps}
		/>
	);
}

export default AppodealNativeAd;

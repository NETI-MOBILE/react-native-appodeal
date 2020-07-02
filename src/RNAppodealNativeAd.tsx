import React from 'react';
import { requireNativeComponent, StyleProp, ViewStyle } from 'react-native';


interface AppodealNativeAdProps {
	textColor?: string;
	textColorMore?: string;
	style?: StyleProp<ViewStyle>
}

const RNAppodealNativeView = requireNativeComponent('RNAppodealNativeView');

const AppodealNativeAd = (props: AppodealNativeAdProps) => {
	const {
		textColor,
		textColorMore,
		style,
		...restProps
	} = props

	return (
		<RNAppodealNativeView
			style={[style, {height: 300}]}
			{...restProps}
		/>
	);
}

export default AppodealNativeAd;

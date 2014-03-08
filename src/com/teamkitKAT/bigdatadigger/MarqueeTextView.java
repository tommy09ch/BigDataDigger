package com.teamkitKAT.bigdatadigger;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.TextView;

public class MarqueeTextView extends TextView {
	public MarqueeTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
		rotate();
	}

	public MarqueeTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
		rotate();
	}

	public MarqueeTextView(Context context) {
		super(context);
		init();
		rotate();
	}

	private void rotate() {
		// TODO Auto-generated method stub
		setSelected(true);
	}

	private void init() {
		if (!isInEditMode()) {

		}
	}

}

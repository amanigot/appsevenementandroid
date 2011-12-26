package fr.clivana.lemansnews.controller;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Gallery;

public class GalleryOneByOne extends Gallery {

	public GalleryOneByOne(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	
	
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		return false;
	}
}

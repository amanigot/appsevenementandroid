package fr.clivana.lemansnews.utils;

import java.text.DecimalFormat;

import android.os.Debug;
import android.util.Log;

public class Debugger {
	
	public static void logHeap() {
	    Double allocated = new Double(Debug.getNativeHeapAllocatedSize())/1048576;
	    Double available = new Double(Debug.getNativeHeapSize())/1048576;
	    Double free = new Double(Debug.getNativeHeapFreeSize())/1048576;
	    DecimalFormat df = new DecimalFormat();
	    df.setMaximumFractionDigits(2);
	    df.setMinimumFractionDigits(2);
	 
	    Log.d("MEM", "debug. =================================");
	    Log.d("MEM", "debug.heap native: allocated " 
	    		+ df.format(allocated) + "MB of " 
	    		+ df.format(available) + "MB (" 
	    		+ df.format(free) + "MB free)");
	    Log.d("MEM", "debug.memory: allocated: " 
	    		+ df.format(new Double(Runtime.getRuntime().totalMemory()/1048576)) + "MB of " 
	    		+ df.format(new Double(Runtime.getRuntime().maxMemory()/1048576))+ "MB (" 
	    		+ df.format(new Double(Runtime.getRuntime().freeMemory()/1048576)) +"MB free)");
	    System.gc();
	    System.gc();
	 
	}
}

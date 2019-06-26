package com.deep.assignment.service;
import org.springframework.stereotype.Service;

import com.github.davidmoten.rx.FileObservable;

import rx.Observable;

@Service
public class TailService {
	public void tailLogFile(String filePath) throws InterruptedException {
		
	    Observable<String> tailer = FileObservable.tailer()
	                                .file(filePath) // absolute path
	                                .tailText();
	   
	    tailer.subscribe(		
	        // onNext()
	            (line) ->System.out.println("Printing Next word: " + line)
	        ,
	        // onError()
	        	(e) -> {
	        			System.out.println("Printing Next word: " + e);
	        			e.printStackTrace();
	        		   }
	    );
	    Thread.sleep(120000);
	}
}

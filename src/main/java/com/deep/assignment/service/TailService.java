package com.deep.assignment.service;
import org.springframework.stereotype.Service;

import com.github.davidmoten.rx.FileObservable;

import rx.Observable;
import rx.functions.Action1;

@Service
public class TailService {
	public void tailLogFile(String filePath) throws InterruptedException {
		
	    Observable<String> tailer = FileObservable.tailer()
	                                .file(filePath) // absolute path
	                                .tailText();
	   
	    tailer.subscribe(		
	        // onNext()
	        new Action1<String>() {
	            public void call(String line) {
	                System.out.println("Printing Next word: " + line);
	            }
	        },
	        // onError()
	        new Action1<Throwable>() {
	            public void call(Throwable e) {
	                System.out.println("Error: " + e);
	                e.printStackTrace();
	            }
	        }
	    );
	    Thread.sleep(120000);
	}
}

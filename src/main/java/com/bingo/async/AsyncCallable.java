package com.bingo.async;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.WebAsyncTask;

@Controller
@RequestMapping("/async/callable")
public class AsyncCallable {

	@RequestMapping("/response-body")
	public @ResponseBody Callable<String> callable() {

		return new Callable<String>() {
			@Override
			public String call() throws Exception {
				Thread.sleep(2000);
				return "Callable result";
			}
		};
	}

//	@RequestMapping("/response-body")
//	public @ResponseBody String callable() throws Exception{
//		Thread.sleep(2000);
//		return "Callable result";
//	}
	
	@RequestMapping("/view")
	public Callable<String> callableWithView(final Model model) {

		return new Callable<String>() {
			@Override
			public String call() throws Exception {
				Thread.sleep(2000);
				model.addAttribute("foo", "bar");
				model.addAttribute("fruit", "apple");
				return "views/html";
			}
		};
	}

	@RequestMapping("/exception")
	public @ResponseBody Callable<String> callableWithException(
			final @RequestParam(required = false, defaultValue = "true") boolean handled) {

		return new Callable<String>() {
			@Override
			public String call() throws Exception {
				Thread.sleep(2000);
				if (handled) {
					// see handleException method further below
					throw new IllegalStateException("Callable error");
				} else {
					throw new IllegalArgumentException("Callable error");
				}
			}
		};
	}

	@RequestMapping("/custom-timeout-handling")
	public @ResponseBody WebAsyncTask<String> callableWithCustomTimeoutHandling() {

		Callable<String> callable = new Callable<String>() {
			@Override
			public String call() throws Exception {
				Thread.sleep(2000);
				return "Callable result";
			}
		};

		return new WebAsyncTask<String>(1000, callable);
	}

	@ExceptionHandler
	@ResponseBody
	public String handleException(IllegalStateException ex) {
		return "Handled exception: " + ex.getMessage();
	}
	
	@ResponseBody
    @RequestMapping("call")
    public Callable<String> call(final HttpServletRequest req, final HttpServletResponse res) throws Exception {
        return new Callable<String>() {
            @Override
            public String call() throws Exception {
            	System.out.println(req.getAuthType());
                TimeUnit.SECONDS.sleep(5);
                return "hello,callable";
            }
        };

    }
}
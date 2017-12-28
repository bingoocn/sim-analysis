package com.cngc.simanalysis.controller;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.cngc.simanalysis.domain.Sample;
import com.cngc.simanalysis.repository.SampleRepository;
import com.cngc.simanalysis.service.SampleService;


@Controller
@RequestMapping("/sample")
public class SampleController {
    protected Logger logger = LoggerFactory.getLogger(SampleController.class);
    @Autowired
    private SampleRepository sampleRepository;
    @Autowired
    private SampleService sampleService;

    @GetMapping
    public ModelAndView list() {
        Iterable<Sample> samples = sampleRepository.findAll();
        return new ModelAndView("sample/list", "samples", samples);
    }

    @GetMapping("sseList")
    public String sseList(@ModelAttribute Sample sample) {
        return "sample/sseList";
    }

    @GetMapping("sse")
    public SseEmitter sse() {
        SseEmitter emitter = new SseEmitter((long) -1);
        emitter.onCompletion(new Runnable() {
            @Override
            public void run() {
                System.out.println("sse complete");
            }
        });
        // 调用业务方法,异步执行.
        sampleService.asyncSse(emitter);
        return emitter;
    }

    @GetMapping("form")
    public String createForm(@ModelAttribute Sample sample) {
        return "sample/form";
    }

    @PostMapping("save")
    public ModelAndView create(@Valid Sample sample, BindingResult result,
            RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return new ModelAndView("sample/form", "formErrors", result.getAllErrors());
        }
        sample = sampleRepository.save(sample);
        redirect.addFlashAttribute("globalMessage", "保存成功");
        return new ModelAndView("redirect:/sample/{id}", "id", sample.getId());
    }

    @GetMapping("{id}")
    public ModelAndView view(@PathVariable("id") Long id) {
        logger.debug("查看id为{}的sample信息", id);
        return new ModelAndView("sample/view", "sample", sampleRepository.findOne(id));
    }
}

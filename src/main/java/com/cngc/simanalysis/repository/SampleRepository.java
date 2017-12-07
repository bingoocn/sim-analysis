package com.cngc.simanalysis.repository;

import org.springframework.data.repository.CrudRepository;
import com.cngc.simanalysis.domain.Sample;



public interface SampleRepository extends CrudRepository<Sample, Long> {
}

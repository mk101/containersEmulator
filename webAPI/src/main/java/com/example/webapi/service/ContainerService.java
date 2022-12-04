package com.example.webapi.service;

import com.example.webapi.dto.ContainerDto;
import com.example.webapi.dto.PageDto;
import com.example.webapi.mapper.ContainerMapper;
import com.example.webapi.model.Container;
import com.example.webapi.repository.ContainerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContainerService {

    private final ContainerRepository containerRepository;
    private final ContainerMapper containerMapper;

    public List<PageDto> generatePages(Pageable pageable, Timestamp start, Timestamp end) {
        List<PageDto> pages = new ArrayList<>();
        Page<Container> current = containerRepository.findAllByTimestampBetween(start, end, pageable);

        while (!current.isEmpty()) {
            PageDto pageDto = new PageDto();
            pageDto.setPage(current.getNumber());
            pageDto.setContainers(new ArrayList<>());

            for (Container container : current) {
                ContainerDto containerDto = containerMapper.map(container);

                pageDto.getContainers().add(containerDto);
            }

            pages.add(pageDto);

            pageable = pageable.next();
            current = containerRepository.findAllByTimestampBetween(start, end, pageable);
        }

        return pages;
    }

    public Timestamp getStartTime() {
        Container container = containerRepository.findById(1).orElseThrow(() -> new RuntimeException("No containers in bd"));
        return container.getTimestamp();
    }
}

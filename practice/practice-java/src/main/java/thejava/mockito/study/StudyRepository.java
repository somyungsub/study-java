package thejava.mockito.study;

import org.springframework.data.jpa.repository.JpaRepository;
import thejava.mockito.domain.Study;

public interface StudyRepository extends JpaRepository<Study, Long> {
}

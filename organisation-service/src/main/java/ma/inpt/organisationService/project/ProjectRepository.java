package ma.inpt.organisationService.project;

import ma.inpt.organisationService.model.response.ProjectListResponseToAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity,Long> {
    List<ProjectEntity> findByOrgId(Long orgId);

    @Query("SELECT new ma.inpt.organisationService.model.response.ProjectListResponseToAdmin(p.title, p.target, o.name, p.currentBalance) " +
            "FROM ProjectEntity p JOIN Organisation o ON p.orgId = o.id")
    List<ProjectListResponseToAdmin> getProjectsWithTheirOrganisationName();
}

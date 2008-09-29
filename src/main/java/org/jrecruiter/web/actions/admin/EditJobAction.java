/*
*	http://www.jrecruiter.org
*
*	Disclaimer of Warranty.
*
*	Unless required by applicable law or agreed to in writing, Licensor provides
*	the Work (and each Contributor provides its Contributions) on an "AS IS" BASIS,
*	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied,
*	including, without limitation, any warranties or conditions of TITLE,
*	NON-INFRINGEMENT, MERCHANTABILITY, or FITNESS FOR A PARTICULAR PURPOSE. You are
*	solely responsible for determining the appropriateness of using or
*	redistributing the Work and assume any risks associated with Your exercise of
*	permissions under this License.
*
*/
package org.jrecruiter.web.actions.admin;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jrecruiter.model.Industry;
import org.jrecruiter.model.Job;
import org.jrecruiter.model.Region;
import org.jrecruiter.web.WebUtil;
import org.texturemedia.smarturls.Result;

/**
 * Edit a job. Save the changes or delete the job posting altogether.
 *
 * @author Gunnar Hillert
 * @version $Id$
 *
 */
@Result(name="success", location="show-jobs", type="redirectAction")
public class EditJobAction extends JobBaseAction {

    /** serialVersionUID. */
    private static final long serialVersionUID = 2621352739536377825L;

    /**
     * Logger Declaration.
     */
    private final Log LOGGER = LogFactory.getLog(EditJobAction.class);

    /**
     * Delete the job.
     */
    public String delete() {

         final Job jobFromDB = jobService.getJobForId(this.model.getJob().getId());

         if (jobFromDB == null) {
             throw new IllegalArgumentException("Job with id " + model.getJob().getId() + " does not exist.");
         }

         jobService.deleteJobForId(this.model.getJob().getId()); //FIXME SECURITY
         super.addActionMessage(getText("job.delete.success"));
         return SUCCESS;
    }

    /**
     * Retrieve a job for edting.
     */
    public String execute() {

        if (this.id == null) {
            throw new IllegalArgumentException("No job id was provided.");
        }

        final Job jobFromDb = jobService.getJobForId(this.id);

        if (jobFromDb != null) {
            LOGGER.info("Loaded job with Id: " + jobFromDb.getId());
            this.model.setJob(jobFromDb);
        } else {
            throw new IllegalStateException("No job found for id " + this.id);
        }

        return INPUT;
    }

    /**
     * Save the job.
     */
    public String save() {

        final Job jobFromDB = jobService.getJobForId(model.getJob().getId());

        if (jobFromDB == null) {
            throw new IllegalArgumentException("Job with id " + model.getJob().getId() + " does not exist.");
        }

        if (this.model.getJob().getIndustry() != null && this.model.getJob().getIndustry().getId() != null) {
            final Industry industry = jobService.getIndustry(jobFromDB.getIndustry().getId());

            if (industry == null) {
                throw new IllegalArgumentException("Industry with id " + this.model.getJob().getIndustry().getId() + " does not exist.");
            } else {
                jobFromDB.setIndustry(model.getJob().getIndustry());
            }
        }

        if (this.model.getJob().getRegion() != null && this.model.getJob().getRegion().getId() != null) {
            final Region region = jobService.getRegion(this.model.getJob().getRegion().getId());

            if (region == null) {
                throw new IllegalArgumentException("Region with id " + this.model.getJob().getRegion().getId() + " does not exist.");
            } else {
                jobFromDB.setRegion(model.getJob().getRegion());
            }
        }

        final String jobDescription = model.getJob().getDescription();

        jobFromDB.setBusinessName(model.getJob().getBusinessName());
        jobFromDB.setRegionOther(model.getJob().getRegionOther());
        jobFromDB.setJobTitle(model.getJob().getJobTitle());
        jobFromDB.setSalary(model.getJob().getSalary());
        jobFromDB.setDescription(WebUtil.stripTags(jobDescription, "<b>"));
        jobFromDB.setWebsite(model.getJob().getWebsite());
        jobFromDB.setBusinessAddress1(model.getJob().getBusinessAddress1());
        jobFromDB.setBusinessAddress2(model.getJob().getBusinessAddress2());
        jobFromDB.setBusinessCity(model.getJob().getBusinessCity());
        jobFromDB.setBusinessState(model.getJob().getBusinessState());
        jobFromDB.setBusinessZip(model.getJob().getBusinessZip());
        jobFromDB.setBusinessPhone(model.getJob().getBusinessPhone());
        jobFromDB.setBusinessEmail(model.getJob().getBusinessEmail());
        jobFromDB.setJobRestrictions(model.getJob().getJobRestrictions());

        if (model.getJob().getUsesMap()) {
            jobFromDB.setUsesMap(Boolean.TRUE);
            jobFromDB.setLongitude(model.getJob().getLongitude());
            jobFromDB.setLatitude(model.getJob().getLatitude());
            jobFromDB.setZoomLevel(model.getJob().getZoomLevel());
        } else {
            jobFromDB.setUsesMap(Boolean.FALSE);
            jobFromDB.setLongitude(null);
            jobFromDB.setLatitude(null);
            jobFromDB.setZoomLevel(null);
        }

        jobFromDB.setUpdateDate(new Date());
        jobService.updateJob(jobFromDB);

        super.addActionMessage(getText("job.edit.success", ""));

        return SUCCESS;

    }

}

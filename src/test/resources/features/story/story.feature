@deleteAllServices @addPivotalTrackerService @softAssert
Feature: Story Item Test

  Background: Preconditions For Story Config
    Given I send a POST request to /projects
      | name                              | AT01 project-01 |
      | iteration_length                  | 1               |
      | week_start_day                    | Monday          |
      | point_scale                       | 0,1,2,3         |
      | start_date                        | 2016-08-29      |
      | number_of_done_iterations_to_show | 12              |
      | initial_velocity                  | 10              |
    And I store as Project1
    And I send a POST request with list to /projects/[Project1.id]/stories
      | name            | current_state | estimate | story_type |
      | AT - 01 story01 | started       | 2        | feature    |
      | AT - 01 story02 | started       |          | bug        |
      | AT - 01 story03 | started       | 3        | feature    |

    And Synchronize Mach2 with Pivotal Tracker description AT01-PivotalTracker

  @deleteAllProjects @deleteAllBoards
  Scenario: C146_Verify all project information display in the dropdown option
    When I add a table widget with the STORY option without configuration
    And I send a GET request to /projects
    Then Verify the projects quantity
    And Verify all information displayed in the project dropdown field

  @deleteAllProjects @deleteAllBoards
  Scenario: C152_Verify that the iteration count showed to 'iteration' drop down list on M2 is the same as on PT
    When I add a table widget with the STORY option
      | PROJECTS | AT01 project-01 |
    Then Verify the iterations quantity [Project1.name]
    And Consolidate all soft assert results


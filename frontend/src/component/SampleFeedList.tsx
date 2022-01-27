import { Grid } from 'semantic-ui-react'
import { Feed, Icon } from 'semantic-ui-react'

export default function SampleFeedList ({ list }: any) {
  return (
    <>
      <Grid columns={1}>
        <Grid.Row>
          {list.map((item: any) => (
            <Grid.Column key={item.id}>
              <Feed>
                <Feed.Event>
                  <Feed.Label image='/images/디폴트프로필사진.png' />
                  <Feed.Content>
                    <Feed.Summary>
                      <a>User name: </a> { item.description }
                      <Feed.Date>4 days ago</Feed.Date>
                    </Feed.Summary>
                    <Feed.Extra images>
                      <a>
                        <img src='/images/로고만.png' />
                      </a>
                    </Feed.Extra>
                    <Feed.Meta>
                      <Feed.Like>
                        <Icon name='like' />1 Like
                      </Feed.Like>
                      &nbsp;&nbsp;&nbsp;#tagname
                    </Feed.Meta>
                  </Feed.Content>
                </Feed.Event>
              </Feed>
            </Grid.Column>
          ))}
          
        </Grid.Row>
      </Grid>
    </>
  );
}
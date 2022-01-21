import { Grid } from 'semantic-ui-react'

export default function BoardList ({ list }) {
  return (
    <>
      <Grid columns={3}>
        <Grid.Row>
          {list.map((item) => (
            <Grid.Column>
              <img src={item.image_link} alt={item.name} />
              UserName
              <p>{ item.description }</p>
            </Grid.Column>
          ))}
          
        </Grid.Row>
      </Grid>
    </>
  );
}
import { Grid } from 'semantic-ui-react'

export default function BoardList ({ list }:any) {
  return (
    <>
      <Grid columns={3}>
        <Grid.Row>
          {list.map((item: { image_link: string | undefined; name: string | undefined; }) => (
            <Grid.Column key={item.image_link}>
              <img src={item.image_link} alt={item.name} />
            </Grid.Column>
          ))}
          
        </Grid.Row>
      </Grid>
    </>
  );
}
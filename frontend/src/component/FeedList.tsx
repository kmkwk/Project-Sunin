import Link from 'next/link';
import { Grid } from 'semantic-ui-react'

export default function BoardList ({ list }) {
  return (
    <>
      <Grid columns={3} stackable>
        <Grid.Row>
          {list.map((item) => (
            <Grid.Column>
              <Link href={`/feed/personal/${item.id}`}>
                <a>
                <img src={item.image_link} alt={item.name} />
                <p>{ item.name }</p>
                </a>
              </Link>
            </Grid.Column>
          ))}
          
        </Grid.Row>
      </Grid>
    </>
  );
}